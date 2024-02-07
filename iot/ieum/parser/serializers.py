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
        

class PillSerializer(serializers.ModelSerializer):
    class PillTimeSerializer(serializers.ModelSerializer):
        class Meta:
            model = Pill
            fields = '__all__'
    
    article = ArticleSerializer(read_only=True)
    
    class Meta:
        model = Comment
        fields = '__all__'
        
        
        
        
class ArticleSerializer(serializers.ModelSerializer):
    comment_set = CommentSerializer(many=True, read_only=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    
    class Meta:
        model = Article
        fields = '__all__'


