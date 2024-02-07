from django.db import models
from datetime import date

class Event(models.Model):
    eventName = models.CharField(max_length=30)
    eventDate = models.DateField()
 

class Meal(models.Model):
    mealTime1 = models.IntegerField(null=True)
    mealTime2 = models.IntegerField(null=True)
    mealTime3 = models.IntegerField(null=True)


class Pill(models.Model):
    pillName = models.CharField(max_length=30)
    pillStartDate = models.DateField()
    pillEndDate = models.DateField()
    pillDate = models.CharField(max_length=8)
    pillMethod = models.CharField(max_length=10)

        
class PillTime(models.Model):
    pillTime = models.IntegerField(null=True)
    pill = models.ForeignKey(Pill, on_delete=models.CASCADE, related_name='pilltimes')

        
class Sleep(models.Model):
    sleepStartTime = models.IntegerField(null=True)
    sleepEndTime = models.IntegerField(null=True)
