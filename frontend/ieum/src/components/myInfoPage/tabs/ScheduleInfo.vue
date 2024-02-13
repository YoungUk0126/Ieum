<script setup>
    import ScheduleListItem from './ScheduleListItem.vue';
    import { getAllList, deleteSchedule } from '@/api/schedule';
    import { ref, onMounted } from "vue";

    const scheduleInfoList = ref([]);

    function formatDate(date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}. ${month}. ${day}`; // 원하는 형식으로 조합
    }

    function handleDelete(eventNo) {
        console.log("삭제 요청 >> ", eventNo);
        deleteSchedule(eventNo,
            (response) => {
                if (response.data.success) {
                    console.log("삭제가 성공했습니다.", response.data.success);
                    getAllSchedule();
                } else {
                    console.log("삭제가 실패했습니다. 응답 상태 코드:", response.status);
                }
            },
            (error) => {
                console.error("삭제 요청 중 에러가 발생했습니다:", error);
            }
        );
    }

    function getAllSchedule() {
        getAllList(
            (response) => {
                console.log("불러온 값", response.data.data);
                scheduleInfoList.value = response.data.data.map(item => ({
                    eventNo: item.eventNo,
                    careNo: item.careNo,
                    eventName: item.eventName,
                    eventDate: new Date(item.eventDate), // LocalDateTime을 JavaScript Date 객체로 변환
                    // 날짜 및 시간 분리
                    date: formatDate(new Date(item.eventDate)),
                    time: new Date(item.eventDate).toLocaleTimeString() // 시간
                }));
            });
    }

    onMounted(() => {
        console.log("Schedule Info onMounted");
        getAllSchedule();
    });
</script>

<template>
    <div v-if="scheduleInfoList.length!=0">
        <ScheduleListItem v-for="scheduleInfo in scheduleInfoList" :key="scheduleInfo.eventNo" :schedule="scheduleInfo" @delete="handleDelete"></ScheduleListItem>
    </div>
    <div v-else>
        등록된 일정 정보가 없습니다.
    </div>
</template>