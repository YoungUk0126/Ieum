from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from django.shortcuts import get_object_or_404, get_list_or_404
from .models import Event, Meal, Pill, PillTime, Sleep
from .serializers import EventListSerializer, MealListSerializer, PillListSerializer, PillTimeSerializer, SleepListSerializer
from django.shortcuts import render, redirect
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import json
from datetime import datetime
from django.conf import settings



@api_view(['GET', 'POST'])
def getdata(request):
    if request.method == 'GET':
        # articles = Article.objects.all()
        articles = get_list_or_404(Event)
        serializer = ArticleListSerializer(articles, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        serializer = ArticleSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        # return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)



@api_view(['GET', 'DELETE', 'PUT'])
def parse(request):
    # article = Article.objects.get(pk=article_pk)
    article = get_object_or_404(Article, pk=article_pk)

    if request.method == 'GET':
        serializer = ArticleSerializer(article)
        return Response(serializer.data)

    elif request.method == 'DELETE':
        article.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

    elif request.method == 'PUT':
        serializer = ArticleSerializer(article, data=request.data, partial=True)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data)


# @api_view(['GET'])
# def comment_list(request):
#     if request.method == 'GET':
#         comments = Comment.objects.all()
#         serializer = CommentSerializer(comments, many=True)
#         return Response(serializer.data)
    


# @api_view(['GET', 'DELETE', 'PUT'])
# def comment_detail(request, comment_pk):
#     comment = Comment.objects.get(pk=comment_pk)
    
#     if request.method == 'GET':
#         serializer = CommentSerializer(comment)
#         return Response(serializer.data)
    
#     elif request.method == 'DELETE':
#         comment.delete()
#         return Response(status=status.HTTP_204_NO_CONTENT)
    
#     elif request.method == 'PUT':
#         serializer = CommentSerializer(comment, data=request.data)
#         if serializer.is_valid(raise_exception=True):
#             serializer.save()
#             return Response(serializer.data)
    
@api_view(['GET'])
def parser(request):
    jsonObj = json.loads(request)

    if jsonObj.get("type") == "Event":
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
            return Response()
    

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
    else:
        print("type : " + jsonObj.get("type"))
        print("content : " + jsonObj.get("content"))



@api_view(['POST'])
def storedata(request):
    alarm = Article.objects.get(pk=article_pk)
    serializer = CommentSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save(article=article)
        return Response(serializer.data, status=status.HTTP_201_CREATED)