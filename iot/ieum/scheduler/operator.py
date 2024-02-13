from apscheduler.schedulers.background import BackgroundScheduler
from parser.models import *
from api.services import tts
from parser.pub import publish
import time
from api.services import dataCheck

scheduler = BackgroundScheduler()

def regularEvent():
    events = Event.objects.filter(eventDate=date.today())
    ment = ''
    if events.exists():
        ment += "오늘은 기념일이 있습니다."
        for e in events:
            ment += e.eventName + ","
        ment += "이 있습니다."
        path = tts(ment)
        publish("EventAlert", path)
    else:
        ment += "오늘은 일정이 없습니다."
        path = tts(ment)
        publish("EventAlert", path)

@scheduler.scheduled_job('cron', minute='*/5')
def update():
    dataCheck()

@scheduler.scheduled_job('cron', minute='*/1')
def start():
    now = time
    sec = now.localtime().tm_hour * 3600 + now.localtime().tm_min * 60

    def meal():
        ment = ''
        meal1 = Meal.objects.filter(mealTime1__range=(sec, sec + 59))
        if meal1.exists():
            ment += "아침 드실 시간이에요."
            path = tts(ment)
            publish("MealAlert", path)
        meal2 = Meal.objects.filter(mealTime2__range=(sec, sec + 59))
        if meal2.exists():
            ment += "점심 드실 시간이에요."
            path = tts(ment)
            publish("MealAlert", path)
        meal3 = Meal.objects.filter(mealTime3__range=(sec, sec + 59))
        if meal3.exists():
            ment += "저녁 드실 시간이에요."
            path = tts(ment)
            publish("MealAlert", path)

    def pill():
        ment = ''
        pilltimes = PillTime.objects.filter(pillTime__range=(sec, sec + 59))
        if pilltimes.exists():
            ment += str(now.localtime().tm_hour) + "시 " + str(now.localtime().tm_min) + "분 "
            for pill in pilltimes:
                ment += pill.pill.pillName + ","
            ment += " 드실 시간입니다."
            path = tts(ment)
            publish("PillAlert", path)

    def sleep():
        ment = ''

        sleep2 = Sleep.objects.filter(sleepStartTime__range=(sec, sec + 59))
        if sleep2.exists():
            ment += "주무실 시간이에요. 내일 아침까지 모든 알림을 끌게요."
            path = tts(ment)
            publish("SleepAlert", path)

        sleep3 = Sleep.objects.filter(sleepEndTime__range=(sec, sec + 59))
        if sleep3.exists():
            ment += "좋은 아침입니다. 안녕히 주무셨어요?"

            events = Event.objects.filter(eventDate=date.today())
            if events.exists():
                ment += "오늘은 기념일이 있습니다."
                for e in events:
                    ment += e.eventName + ","
                ment += "이 있습니다."
                # path = tts(ment)
                # publish("EventAlert", path)
            else:
                ment += "오늘은 기념일이 없습니다."
            path = tts(ment)
            publish("SleepAlert", path)
            regularEvent()

    def anyEvent():
        ment = ''
        anyEvent = AnyEvent.objects.filter(eventDateTime__date=date.today())
        if anyEvent.exists():
            for e in anyEvent:
                event_sec = e.eventDateTime.time().hour * 3600 + e.eventDateTime.time().minute * 60
                if event_sec >= sec and event_sec < sec + 60:
                    ment += str(now.localtime().tm_hour) + "시 " + str(now.localtime().tm_min) + "분 " + e.eventName + " 일정이 있습니다"
                    path = tts(ment)
                    publish("AnyEventAlert", path)

    meal()
    sleep()
    pill()
    anyEvent()

scheduler.start()