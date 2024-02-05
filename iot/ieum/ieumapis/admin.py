# 관리자 페이지 설정
from django.contrib import admin

from .models import Pill, Meal, Event, Sleep
# Register your models here.
admin.site.register(Pill, Meal, Event, Sleep)