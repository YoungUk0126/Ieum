<script setup>
    import EventList from './EventListItem.vue';
    import { getAllEvent, deleteEvent } from '@/api/modalAlarms/event';
    import { ref, onMounted } from "vue";

    const eventInfoList = ref([]);

    function handleDelete(eventNo) {
        console.log("삭제 요청 >> ", eventNo);
        deleteEvent(eventNo,
            (response) => {
                if (response.data.success) {
                    console.log("삭제가 성공했습니다.", response.data.success);
                    getAll();
                } else {
                    console.log("삭제가 실패했습니다. 응답 상태 코드:", response.status);
                }
            });
    }

    function getAll() {
        getAllEvent(
            (response) => {
                console.log("불러온 값", response.data.data);
                eventInfoList.value = response.data.data;
            });
    }

    onMounted(() => {
        console.log("Schedule Info onMounted");
        getAll();
    });
</script>

<template>
    <div v-if="eventInfoList.length!=0">
        <EventList v-for="eventInfo in eventInfoList" :key="eventInfo.eventNo" :event="eventInfo" @delete="handleDelete"></EventList>
    </div>
    <div v-else>
        등록된 기념일 정보가 없습니다.
    </div>
</template>