# tasks.py
from apscheduler.schedulers.background import BackgroundScheduler
from django.db.models import Q
from pytz import timezone
from parser.models import *
from parser.services import parser
import datetime


scheduler = BackgroundScheduler()


def update_data(requests):
    # db 내의 데이터의 시각과 대조하여 같은 시각이 되면 정보를 전송

    # 현재 시각을 로컬 시간대로 변환
    current_time = datetime.datetime.now(timezone('Asia/Seoul'))

    # 현재 연-월-일만을 가져옴
    current_date = current_time.date()
    print(current_date)
    
    alarms = []
    # 시, 분, 초 가져오기
    current_hour = current_time.hour
    current_minute = current_time.minute
    current_second = current_time.second
    

    # 현재 시각과 하루의 시작 시각의 차이를 초로 계산
    time_to_seconds_format = (current_hour * 3600) + (current_minute * 60) + (current_second)
    print(time_to_seconds_format)


    total_data = {
        "event" : event_data,
        "meal" : meal_data,
        "pill" : pill_ids,
        "sleep" : sleep_data
    }
    
    url = "https://example.com/endpoint"  # 대상 URL
    
    event_data = Event.objects.all()
    # meal_data = Meal.objects.latest('id')
    # pill_ids = PillTime.objects.filter(pillTime=time_to_seconds_format).values_list('pill_id', flat=True)
    # sleep_data = Sleep.objects.latest('id')
    meal_data = Meal.objects.all()
    pill_ids = PillTime.objects.all()
    sleep_data = Sleep.objects.all()

    response = requests.post(url, json=total_data)  # JSON 데이터를 포함한 POST 요청 보내기

    print(response.status_code)  # 응답 상태 코드 확인


# 스케줄링 작업 등록
scheduler.add_job(update_data, 'interval', seconds=5)


# 스케줄링 작업 실행
scheduler.start()