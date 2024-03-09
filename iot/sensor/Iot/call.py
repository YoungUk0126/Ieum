import RPi.GPIO as GPIO
import time
import threading
import subprocess
import pygame
import screen

import sound_output

button_pin = 15  # 버튼이 연결된 GPIO 핀 번호
# 웹 브라우저 프로세스를 저장할 변수
browser_process = None

GPIO.setmode(GPIO.BCM)
GPIO.setup(button_pin, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)


def answer_call():
    global browser_process
    if browser_process is None or browser_process.poll() is not None:
        # 웹 브라우저가 실행 중이지 않으면 시작
        time.sleep(0.1)
        sound_output.soundPlay('connect_sound.wav')
        browser_process = open_browser()
        return True
    else:
        # 웹 브라우저가 실행 중이면 종료
        sound_output.soundPlay('disconnect.wav')
        browser_process.terminate()
        browser_process = None
        screen.display_image('happy.jpg')
        return False


def open_browser():
    url = 'https://i10a303.p.ssafy.io:443/callcare?serial=aaaaaa'
    return subprocess.Popen(['chromium-browser', '--kiosk', '--noerrdialogs', '--disable-infobars', url])


def detectedBtn(bell: pygame.mixer.Sound):
    btnFlag = True
    start_time = time.time()
        
    while btnFlag:
        if (time.time() - start_time) > 30:
            break
        if GPIO.input(button_pin) == GPIO.HIGH:
            sound_output.soundStop(bell)
            btnFlag = answer_call()
        time.sleep(0.1)
