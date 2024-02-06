import json
from .models import *
from datetime import date

def parser(data):
    jsonObj = json.loads(json.dumps(data))
    if(jsonObj.get("type") == "Event"):
        jsonArr = jsonObj.get("content").get("List")
        for list in jsonArr:
            event = Event.objects.create()
            event.eventName = list.get("eventName")
            eventDate = ''
            for date in list.get("eventDate"):
                eventDate += (str(date) + '-')
            event.eventDate = eventDate[:-1]
            event.save()

    elif(jsonObj.get("type") == "Sleep"):
        jsonData = jsonObj.get("content")
        sleep = Sleep.objects.create()
        sleep.sleepStartTime = jsonData.get("sleepStartTime")
        sleep.sleepEndTime = jsonData.get("sleepEndTime")
        sleep.save()

    elif(jsonObj.get("type") == "Meal"):
        jsonData = jsonObj.get("content")
        meal = Meal.objects.create()
        meal.mealTime1 = jsonData.get("mealTime1")
        meal.mealTime2 = jsonData.get("mealTime2")
        meal.mealTime3 = jsonData.get("mealTime3")
        meal.save()

    elif(jsonObj.get("type") == "Pill"):
        jsonData = jsonObj.get("content").get("contents")
        for list in jsonData:
            pill = Pill.objects.create()
            pill.pillName = list.get("pillName")
            pillStartDate = ''
            for date in list.get("pillStartDate"):
                pillStartDate += (str(date) + '-')
            pill.pillStartDate = pillStartDate[:-1]
            pillEndDate = ''
            for date in list.get("pillEndDate"):
                pillEndDate += (str(date) + '-')
            pill.pillEndDate = pillEndDate[:-1]
            pill.pillDuration = list.get("pillDate")
            pill.pillMethod = list.get("pillMethod")

            for times in list.get("pillTimes"):
                time = PillTime.objects.create()
                time.pillTime = times.get("pillTakeTime")