import RPi.GPIO as GPIO
import time

button_pin = 15  # 버튼이 연결된 GPIO 핀 번호
# 웹 브라우저 프로세스를 저장할 변수
browser_process = None

GPIO.setmode(GPIO.BCM)
GPIO.setup(button_pin, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)


while True:
	print("Button wait")
	if GPIO.input(button_pin) == GPIO.HIGH:
		print("Button pushed!")
		break
	time.sleep(0.1)
