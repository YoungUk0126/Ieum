# tasks.py

from apscheduler.schedulers.background import BackgroundScheduler

scheduler = BackgroundScheduler()


def update_data():
    # 데이터 업데이트 작업 수행
    print("Data updated successfully!")


# 스케줄링 작업 등록
scheduler.add_job(update_data, 'interval', seconds=5)


# 스케줄링 작업 실행
scheduler.start()