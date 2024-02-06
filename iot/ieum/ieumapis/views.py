'''
MVC의 controller 역할 / MTV의 views의 역할
# 각종 기능 관련 함수들을 작성
# templates에서 외형을, models.py에서 데이터를 받아 조합해서 객체를 생성해서 응답
'''
from django.shortcuts import render # 페이지 생성 함수
import json

# 주어진 JSON 데이터
json_data1 = '{"type":"Pill","content":{"contents":{"rage:{"pillName":"감기약","pillStartDate":[2024,2,1],"pillEndDate":[2024,2,20],"pillDate":"11010100","pillMethod":"식전","pillTimes":{"pillTakeTime":28800,"pillTakeTime":43200,"pillTakeTime":64800}}},"pillName":"위장약","pillStartDate":[2024,2,1],"pillEndDate":[2024,2,22],"pillDate":"11010001","pillMethod":"식전","pillTimes":{"pillTakeTime":28800,"pillTakeTime":43200,"pillTakeTime":64800}}}}'
json_data2 = '{"type":"Meal","content":{"mealTime1":27000,"mealTime2":43200,"mealTime3":61200}}'
json_data3 = '{"type":"Event","content":{"list":{"rage:{"eventName":"결혼기념일","eventDate":[2024,2,12]},"eventName":"생일","eventDate":[2024,3,16]}}}'
json_data4 = '{"type":"Sleep","content":{"sleepStartTime":21600,"sleepEndTime":32400}}'

# JSON 데이터 정렬
parsed_data1 = json.loads(json_data1)
sorted_json_data1 = json.dumps(parsed_data1, indent=2, sort_keys=True)

parsed_data2 = json.loads(json_data2)
sorted_json_data2 = json.dumps(parsed_data2, indent=2, sort_keys=True)

parsed_data3 = json.loads(json_data3)
sorted_json_data3 = json.dumps(parsed_data3, indent=2, sort_keys=True)

parsed_data4 = json.loads(json_data4)
sorted_json_data4 = json.dumps(parsed_data4, indent=2, sort_keys=True)

# 정렬된 JSON 데이터 출력
print("Sorted JSON Data 1:")
print(sorted_json_data1)

print("\nSorted JSON Data 2:")
print(sorted_json_data2)

print("\nSorted JSON Data 3:")
print(sorted_json_data3)

print("\nSorted JSON Data 4:")
print(sorted_json_data4)


# Create your views here.
def getAllJsonData(request):
    # JSON 데이터 정렬
    parsed_data1 = json.loads(json_data1)
    sorted_json_data1 = json.dumps(parsed_data1, indent=2, sort_keys=True)

    parsed_data2 = json.loads(json_data2)
    sorted_json_data2 = json.dumps(parsed_data2, indent=2, sort_keys=True)

    parsed_data3 = json.loads(json_data3)
    sorted_json_data3 = json.dumps(parsed_data3, indent=2, sort_keys=True)

    parsed_data4 = json.loads(json_data4)
    sorted_json_data4 = json.dumps(parsed_data4, indent=2, sort_keys=True)  
    jsondata = request.GET.get()
    context = {
        'name':'Jane'
    }
    return render(request, 'apis/index.html',context)