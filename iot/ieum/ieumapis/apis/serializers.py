from django.contrib.auth.models import Group, User
from rest_framework import serializers
from ..models import Pill, PillTimes, Meal, Event, Sleep


class PillSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pill
        fields = '__all__'
        


class PillTimesSerializer(serializers.ModelSerializer):
    class Meta:
        model = PillTimes
        fields = '__all__'


class MealSerializer(serializers.ModelSerializer):
    class Meta:
        model = Meal
        fields = '__all__'


class EventSerializer(serializers.ModelSerializer):
    class Meta:
        model = Event
        fields = '__all__'
              
              
class SleepSerializer(serializers.ModelSerializer):
    class Meta:
        model = Sleep
        fields = '__all__'