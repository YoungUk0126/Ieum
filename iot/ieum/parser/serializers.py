from .models import Event, Meal, Pill, PillTime, Sleep
from rest_framework import serializers


class EventListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Event
        fields = '__all__'


class MealListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Meal
        fields = '__all__'


class PillTimeSerializer(serializers.ModelSerializer):
    class PillListSerializer(serializers.ModelSerializer):
        class Meta:
            model = Pill
            fields = '__all__'
    
    # override
    pill = PillListSerializer(read_only=True)
    
    class Meta:
        model = PillTime
        fields = '__all__'
        
        
class PillListSerializer(serializers.ModelSerializer):
    pilltime_set = PillTimeSerializer(many=True, read_only=True)
   
    class Meta:
        model = Pill
        fields = '__all__'
        

class SleepListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Sleep
        fields = '__all__'
        
        



