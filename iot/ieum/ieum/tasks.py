# tasks.py
from apscheduler.schedulers.background import BackgroundScheduler
from django.db.models import Q
from pytz import timezone
from parser.models import *
from parser.services import parser
import datetime


scheduler = BackgroundScheduler()

# 1. 데이터를 가져옴


# 2. 받은 데이터를 파싱하는 작업
def get_data():
    parser()
    

def update_data():
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

    # 데이터베이스에서 시각 관련 데이터 조회
    event_data = Event.objects.filter(eventDate=current_date)
    meal_data = Meal.objects.filter(Q(mealTime1=time_to_seconds_format) | Q(mealTime2=time_to_seconds_format,) | Q(mealTime3=time_to_seconds_format))
    pill_ids = PillTime.objects.filter(pillTime=time_to_seconds_format).values_list('pill_id', flat=True)
    sleep_data = Sleep.objects.filter(Q(sleepStartTime=time_to_seconds_format) | Q(sleepEndTime=time_to_seconds_format) )

    # 조회된 데이터에 대한 작업 수행
    for item in event_data:
        # 작업 수행 예시: 데이터 출력
        print(item)
    # 조회된 데이터에 대한 작업 수행
    for item in meal_data:
        # 작업 수행 예시: 데이터 출력
        print(item)
    
    # 조회된 데이터에 대한 작업 수행
    for item in pill_ids:
        # 작업 수행 예시: 데이터 출력
        print(item)
        
    # 조회된 데이터에 대한 작업 수행
    for item in sleep_data:
        # 작업 수행 예시: 데이터 출력
        print(item)

    


# 스케줄링 작업 등록
scheduler.add_job(update_data, 'interval', seconds=5)


# 스케줄링 작업 실행
scheduler.start()