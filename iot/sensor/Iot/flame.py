import time
import RPi.GPIO as GPIO
import parser
import sound_output, screen


GPIO.setmode(GPIO.BCM)  # 핀모드 설정

# 사용할 GPIO 핀의 번호를 설정
flame_pin = 21


GPIO.setwarnings(False)
# 버튼 핀의 입력설정 , PULL DOWN 설정
GPIO.setup(flame_pin, GPIO.IN)


def flameCallBack(flame_pin):
    # 불 감지했으니 장고로 불났다는 정보를 보내는 로직이 필요
    parser.publish("Fire", "fire!!")
    p = sound_output.soundPlay('fire.mp3')
    screen.display_image('sad.jpg')
    time.sleep(5)
    screen.display_image('happy.jpg')
    sound_output.soundStop(p)


# 불꽃 감지 센서
def detectedFlame():
    GPIO.add_event_detect(flame_pin, GPIO.BOTH, bouncetime=3000)
    GPIO.add_event_callback(flame_pin, flameCallBack)
    while True:
        time.sleep(1)
