'''
MVC의 controller 역할 / MTV의 views의 역할
# 각종 기능 관련 함수들을 작성
# templates에서 외형을, models.py에서 데이터를 받아 조합해서 객체를 생성해서 응답
'''
from django.shortcuts import render # 페이지 생성 함수


# Create your views here.
def index(request):
    context = {
        'name':'Jane'
    }
    return render(request, 'apis/index.html',context)