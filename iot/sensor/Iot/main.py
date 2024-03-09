from threading import Thread
import voice, call, flame, parser
from screen import display_image

if __name__ == '__main__':
    
    #image_path = 'sad.jpg'
    #display_image(image_path)
    
    # 스레드로 각자 센서들 감지하게 만듬
    detectedVoiceProc = Thread(target=voice.detectedVoice, args=()) # 저기 이음아 감지
    detectedFlameProc = Thread(target=flame.detectedFlame, args=()) # 불 감지
    # Parser도 항상 파싱 기다림
    detectedParserProc = Thread(target=parser.subscribe, args=('aaaaaa',))
    screenProc = Thread(target = display_image, args=('happy.jpg',))
    detectedVoiceProc.start()
    detectedFlameProc.start()
    detectedParserProc.start()
    screenProc.start()
