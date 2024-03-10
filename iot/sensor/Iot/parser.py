import json
import pygame
import call
import sound_output
import voice
import redis

def parser(data):
    jsonObj = json.loads(data)

    if(jsonObj.get("type") == "MealAlert"):
        jsonData = jsonObj.get("content")
        sound_output.soundPlay(jsonData)
    # 오디오 파일 재생 로직( 밥 먹을 시간이에요~~ )

    elif(jsonObj.get("type") == "PillAlert"):
        jsonData = jsonObj.get("content")
        sound_output.soundPlay(jsonData)
    # 오디오 파일 재생 로직( 약 먹을 시간이에요~~ )

    elif(jsonObj.get("type") == "SleepAlert"):
        jsonData = jsonObj.get("content")
        sound_output.soundPlay(jsonData)
    # 오디오 파일 재생 로직( 곧 잘 시간이에요~~ )

    elif(jsonObj.get("type") == "EventAlert"):
        jsonData = jsonObj.get("content")
        sound_output.soundPlay(jsonData)
    # 오디오 파일 재생 로직( 어디 갈 시간이에요~~ )
    
    elif(jsonObj.get("type") == "AnyEventAlert"):
        jsonData = jsonObj.get("content")
        sound_output.soundPlay(jsonData)

    elif (jsonObj.get("type") == "Call"):
        jsonData = jsonObj.get("content")
        p = sound_output.soundPlay('friendSong.mp3')
        call.detectedBtn(p)
    elif (jsonObj.get("type") == "SmallTalk"):
        pass
        # 산발적 대화 기능 있었으면 좋겠어~~
    elif (jsonObj.get("type") == "ChatResponse"):
        jsonData = jsonObj.get("content")
        # 받아온 음성파일 데이터 경로대로 소리 출력해주는 함수
        mixer = sound_output.soundPlay(jsonData)
        # mixer가 계속 실행중이라면 실행이 끝날때까지 대기
        while mixer.get_busy() :
            pass
        # 다시 녹음하고 보내는 파일
        voice.recordVoice()

redis_client = redis.Redis(host='i10a303.p.ssafy.io', port=6379)
def publish(type, content):
    # 얜 뭐임?
    channel = "aaaaaa"
    message = '{"type": "' + type + '", "content":"' + content + '"}'
    #print(message)
    redis_client.publish(channel, message)

def subscribe(topic):
    pubsub = redis_client.pubsub()
    pubsub.subscribe(topic)
    for message in pubsub.listen():
        if message['type'] == 'message':
            # UTF-8로 디코딩하여 출력
            data = message['data'].decode('utf-8')
            #print(f"Received message: {data}")
            parser(data)
