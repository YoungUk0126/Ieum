from django.shortcuts import render
from apscheduler.schedulers.background import BackgroundScheduler

# Create your views here.
sched = BackgroundScheduler()

@sched.scheduled_job('cron',year='해',month='월',day='일',hour='시', minute = '분',second='초' ,name = 'schedulerName')
def schedulerF():
    sched.start()