<template>
    <div class="mt-5 mb-5">
        <div class="datepicker border">
            <Calendar v-model="date" :attributes="attributes" @dayclick="click" expanded>
                <template #footer>

                </template>
            </Calendar>
        </div>
        <div class="container border mt-5 p-5 rounded border-success-subtle">
            <h1>일정 상세</h1>
            <div v-if="schedule.length > 0">
                <div v-for="item in schedule" :key="item">
                    <div class="mt-3 d-flex">
                        <div class="content">{{ item }}</div>
                        <div class="col-2 text-center">
                            <a href="#" class="btn btn-custom-primary btn-lg w-50 fw-bold">수정</a>
                        </div>
                        <div class="col-2 text-center">
                            <a href="#" class="btn btn-custom-danger btn-lg w-50 fw-bold">삭제</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>
  
<script setup>
import { ref, computed } from 'vue';
import { Calendar } from 'v-calendar';
import 'v-calendar/style.css';

const date = ref(new Date());
date.value.setHours(9, 0, 0, 0);

const schedule = ref([]);

const todos = ref([
    // {
    //     description: '주마다 반복되는 일정',
    //     isComplete: false,
    //     dates: { repeat: { weekdays: 7 } },
    //     color: 'blue',
    // },
    {
        description: '첫 번째 일정',
        isComplete: false,
        dates: new Date('2024-01-20'),
        color: 'blue',
    },
    {
        description: '두 번째 일정',
        isComplete: false,
        dates: new Date('2024-01-24'),
        color: 'green',
    },
    {
        description: '세 번째 일정',
        isComplete: false,
        dates: new Date('2024-01-28'),
        color: 'pink',
    },
    {
        description: '네 번째 일정',
        isComplete: false,
        dates: new Date('2024-01-28'),
        color: 'orange',
    }
]);

const attributes = computed(() => [
    ...todos.value.map(todo => ({
        dates: todo.dates,
        dot: {
            color: todo.color,
            ...(todo.isComplete && { class: 'opacity-75' }),
        },
        popover: {
            label: todo.description,
        },
    })),

]);

const click = (clickedDate) => {
    const selectedDate = new Date(clickedDate.id);
    date.value = selectedDate; // 클릭한 날짜로 업데이트
    scheduleCheck();
}

const scheduleCheck = () => {
    // 기존 schedule 비우기
    schedule.value = [];

    for (let i = 0; i < todos.value.length; i++) {
        console.log(date.value);
        console.log(todos.value[i].dates);
        if (date.value.getTime() === todos.value[i].dates.getTime()) {
            console.log("일정이 있습니다.");
            schedule.value.push(todos.value[i].description);
        }
        else {
            console.log("오늘 일정이 아닙니다.");

        }
    }


}
</script>


<style scoped>
.content {
    font-size: 30px;
}

.btn-custom-primary {
    background-color: #27A791;
    color: #fff;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.btn-custom-danger {
    background-color: #A4A4A4;
    color: #fff;
    transition: background-color 0.3s ease, color 0.3s ease;
}
</style>