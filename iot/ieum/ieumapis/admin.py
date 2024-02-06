# 관리자 페이지 설정
from django.contrib import admin

from .models import Pill, PillTimes, Meal, Event, Sleep
# Register your models here.
admin.site.register(Pill)
admin.site.register(PillTimes)
admin.site.register(Meal)
admin.site.register(Event)
admin.site.register(Sleep)
