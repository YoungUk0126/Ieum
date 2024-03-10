import pvporcupine
import pvrecorder
import pyaudio
import wave
import audioop
import time
import parser
import uuid
import sound_output

# 오디오 녹음
def recordVoice():
    sound_output.soundPlay("start_record.wav")
    
    # 녹음할 오디오 스트림을 설정합니다.
    chunk = 1024  # 음성을 처리할 청크 크기
    sample_format = pyaudio.paInt16  # 16비트 형식으로 설정
    channels = 1  # 모노 오디오 설정
    fs = 44100  # 샘플링 주파수 설정

    p = pyaudio.PyAudio()  # PyAudio 객체 생성
    stream = p.open(format=sample_format,
                    channels=channels,
                    rate=fs,
                    frames_per_buffer=chunk,
                    input=True)

    #print("녹음을 시작합니다.")

    frames = []  # 음성 데이터를 저장할 리스트
    MIN_SILENCE = 480  # RMS 임계값을 조정합니다.
    silence_counter = 0
    record_time = 0

    while True:
        data = stream.read(chunk)
        frames.append(data)
        record_time += (chunk / fs)

        rms = audioop.rms(data, 2)  # audioop 라이브러리를 사용하여 RMS 값을 계산합니다.
        if rms < MIN_SILENCE:
            #print("음성 미감지")
            silence_counter += (chunk / fs)  # 실제 시간 단위로 카운터를 증가시킵니다.
        else:
            #print("음성 감지")
            silence_counter = 0

        if silence_counter >= 2:  # 2초 동안 무음이면 녹음을 멈춥니다.
            if record_time <= 3:  # 총 녹음 시간이 3초라면 대화가 없었다고 판단 후
                #print("record finish")
                return      # 더 이상 이 녹음파일을 저장하지도 보내지도 않습니다
        
            break


    print("녹음을 종료합니다.")

    stream.stop_stream()
    stream.close()
    p.terminate()

    # 저장된 음성 데이터를 파일로 저장할 수도 있습니다.
    # 예를 들어, WAV 파일로 저장하려면 다음과 같이 저장할 수 있습니다.

    # UUID 만들어서 보내기
    filename = "/home/ieum/Voice/" + str(uuid.uuid1())
    wf = wave.open(filename, 'wb')
    wf.setnchannels(channels)
    wf.setsampwidth(p.get_sample_size(sample_format))
    wf.setframerate(fs)
    wf.writeframes(b''.join(frames))
    wf.close()

    # 장고한테 JSON보냄
    parser.publish('Chat', filename)
# 음성 감지
def detectedVoice():
    try:
        recorder.start()

        while True:
            # 저기 이음아 감지하면 양수값 반환
            keyword_index = porcupine.process(recorder.read())
            if keyword_index >= 0:
                #print("이음이 감지됨")
                # 응답 녹음 함수
                recordVoice()


    except KeyboardInterrupt:
        recorder.stop()
    finally:
        porcupine.delete()
        recorder.delete()


porcupine = pvporcupine.create(
    access_key= { access-key },
    keyword_paths=['ieum_ko_raspberry-pi_v3_0_0.ppn'],
    model_path='porcupine_params_ko.pv'
)
recorder = pvrecorder.PvRecorder(device_index=-1, frame_length=porcupine.frame_length)
