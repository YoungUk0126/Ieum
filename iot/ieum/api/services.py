from io import BytesIO
from requests_toolbelt import MultipartEncoder
from pydub import AudioSegment
from ieum.keys import get_key
from parser.pub import publish
import requests
import uuid
import pyglet


def tts(text):
    path = get_key("FILE_PATH")
    res = requests.post(get_key("URL") + '/api/ieum/speak', json={"text": text})
    print(res)
    print(res.text)
    audio = BytesIO(res.content)
    exportName = uuid.uuid1()
    exportPath = path + str(exportName) + '.wav'
    seg = AudioSegment.from_file(audio, format="wav")
    seg.export(exportPath, format="wav")
    song = pyglet.media.load(exportPath)
    song.play()


def chat(source):
    path = get_key("FILE_PATH")
    inputPath = path + source + '.wav'
    m  = MultipartEncoder(
        fields={
            'file': (source, open(inputPath, 'rb'), 'audio/wav')
    })
    headers = {'Content-Type': m.content_type}
    endPoint = get_key("URL") + '/api/ieum/' + get_key("SERIAL_CODE")
    res = requests.post(endPoint, data=m, headers=headers)
    audio = BytesIO(res.content)

    exportName = uuid.uuid1()
    exportPath = path + str(exportName) + '.wav'
    seg = AudioSegment.from_file(audio, format="wav")
    seg.export(exportPath, format="wav")

    publish("ChatResponse", exportPath)

    song = pyglet.media.load(exportPath)
    song.play()

def activeCheck():
    serial = get_key("SERIAL_CODE")
    response = requests.post(get_key("URL") + '/api/devices/check-device', json={"serial": serial})
    jsonObj = response.json()
    usable = jsonObj.get("usable")
    if(usable == "True"):
        # 전환 플래그 전송
        print("TRUE")
    else:
        # 재부팅 플래스 전송
        print("FALSE")
def dataCheck():
    serial = get_key("SERIAL_CODE")
    response = requests.post(get_key("URL") + '/api/devices/check-info', json={'serial' : serial})
def fire():
    serial = get_key("SERIAL_CODE")
    response = requests.post(get_key("URL") + '/api/devices/fire', data={'serial' : serial})