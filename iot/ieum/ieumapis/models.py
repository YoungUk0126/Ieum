# DB 모델 정의
from django.db import models

# Create your models here.
'''
class Article(models.Model): # models 모듈의 Model 클래스(model의 모든 코드가 들어있는 클래스)를 상속받음
    title=models.CharField(max_length=50)
    content=models.TextField()

'''    
class Pill(models.Model):
    pillName = models.CharField(max_length=50)
    pillStartDate = models.DateField(auto_now=False, auto_now_add=False)
    pillEndDate = models.DateField(auto_now=False, auto_now_add=False)
    pillDate = models.CharField(max_length=8)
    pillMethod = models.CharField(max_length=20)
    pillTimes = models.JSONField()


class Meal(models.Model):
    mealTime1 = models.IntegerField()
    mealTime2 = models.IntegerField()
    mealTime3 = models.IntegerField()


class Event(models.Model):
    eventName = models.CharField(max_length=50)
    eventDate = models.DateField()


class Sleep(models.Model):
    sleepStartTime = models.IntegerField()
    sleepEndTime = models.IntegerField()
    