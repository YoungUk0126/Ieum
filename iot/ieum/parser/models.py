from django.db import models

class Event(models.Model):
    eventName = models.CharField(max_length=30)
    eventDate = models.DateField()


class Meal(models.Model):
    mealTime1 = models.IntegerField()
    mealTime2 = models.IntegerField()
    mealTime3 = models.IntegerField()


class Pill(models.Model):
    pillName = models.CharField(max_length=30)
    pillStartDate = models.DateField()
    pillEndDate = models.DateField()
    pillDate = models.CharField(max_length=8)
    pillMethod = models.CharField(max_length=10)

class PillTime(models.Model):
    pillTime = models.IntegerField()
    Pill = models.ForeignKey(Pill, on_delete=models.CASCADE, related_name='pilltimes')

class Sleep(models.Model):
    sleepStartTime = models.IntegerField()
    sleepEndTime = models.IntegerField()
