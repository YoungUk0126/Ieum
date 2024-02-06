"""
클라이언트의 요청을 url 형태로 받아내는 곳. 어떤 url이냐에 따라 호출하는 곳이 달라짐. 
URL configuration for ieum project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from ieumapis import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('json/', views.Json, name='json')
    path('api-auth/', include('rest_framework.urls'))
]
