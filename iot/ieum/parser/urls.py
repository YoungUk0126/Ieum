from django.urls import path
from . import views

name='parser'
urlpatterns = [
    path('getdata/', views.getdata, name='getdata'),
    path('parse/', views.parser, name='parse'),
    path('storedata/', views.storedata, name='storedata')
]
