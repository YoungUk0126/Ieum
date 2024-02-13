from .models import *
from datetime import datetime
from api.services import *
import json

def parser(data):
    jsonObj = json.loads(data)

    if(jsonObj.get("type") == "Event"):
        Event.objects.all().delete()
        jsonArr = jsonObj.get("content").get("list")
        for list in jsonArr:
            event = Event.objects.create()
            event.eventName = list.get("eventName")
            eventDate = ''
            for date in list.get("eventDate"):
                eventDate += (str(date) + '-')
            event.eventDate = datetime.strptime(eventDate[:-1],'%Y-%m-%d')
            event.save()

    elif(jsonObj.get("type") == "AnyEvent"):
        AnyEvent.objects.all().delete()
        jsonArr = jsonObj.get("content").get("list")
        for list in jsonArr:
            anyEvent = AnyEvent.objects.create()
            anyEvent.eventName = list.get("eventName")
            eventDate = ''
            for date in list.get("eventDate"):
                eventDate += (str(date) + ", ")
            eventDate += '0'
            eventDate = tuple(map(int, eventDate.split(', ')))
            datetime_object = datetime(*eventDate)
            formatted_date = datetime_object.strftime("%Y-%m-%d %H:%M:%S")
            anyEvent.eventDateTime = formatted_date
            anyEvent.save()

    elif(jsonObj.get("type") == "Sleep"):
        Sleep.objects.all().delete()
        jsonData = jsonObj.get("content")
        sleep = Sleep.objects.create()
        sleep.sleepStartTime = jsonData.get("sleepStartTime")
        sleep.sleepEndTime = jsonData.get("sleepEndTime")
        sleep.save()

    elif(jsonObj.get("type") == "Meal"):
        Meal.objects.all().delete()
        jsonData = jsonObj.get("content")
        meal = Meal.objects.create()
        meal.mealTime1 = jsonData.get("mealTime1")
        meal.mealTime2 = jsonData.get("mealTime2")
        meal.mealTime3 = jsonData.get("mealTime3")
        meal.save()

    elif(jsonObj.get("type") == "Pill"):
        Pill.objects.all().delete()
        PillTime.objects.all().delete()
        jsonData = jsonObj.get("content").get("contents")

        for list in jsonData:
            pill = Pill.objects.create()
            pill.pillName = list.get("pillName")
            pill.pillDate = list.get("pillDate")
            pill.pillMethod = list.get("pillMethod")

            pillStartDate = ''
            for date in list.get("pillStartDate"):
                pillStartDate += (str(date) + '-')
            pill.pillStartDate = datetime.strptime(pillStartDate[:-1],'%Y-%m-%d')

            pillEndDate = ''
            for date in list.get("pillEndDate"):
                pillEndDate += (str(date) + '-')
            pill.pillEndDate = datetime.strptime(pillEndDate[:-1],'%Y-%m-%d')
            pill.save()

            for times in list.get("pillTimes"):
                pilltime = PillTime.objects.create(pill=pill, pillTime=times.get("pillTakeTime"))
                pilltime.save()

    elif(jsonObj.get("type") == "Chat"):
        source = jsonObj.get("content")
        chat(source)

    elif(jsonObj.get("type") == "Fire"):
        source = jsonObj.get("content")
        tts(source)
        # fire()
