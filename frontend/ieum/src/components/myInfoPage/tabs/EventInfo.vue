<script setup>
    import EventList from './EventListItem.vue';
    import { getAllEvent } from '@/api/modalAlarms/event';
    import { ref, onMounted } from "vue";

    const eventInfoList = ref([]);

    onMounted(() => {
        // 추후에 api 조회 호출
        console.log("Schedule Info onMounted");
        getAllEvent(
        (response) => {
            console.log("불러온 값", response.data.data);
            eventInfoList.value = response.data.data;
        }, (error) => {
            console.log("Event Info 불러오던 중 Error 발생!!");
        })
        
    });
</script>

<template>
    <div v-if="eventInfoList">
        <EventList v-for="eventInfo in eventInfoList" :key="eventInfo.id" :event="eventInfo"></EventList>
    </div>
    <div v-else>
        등록된 기념일 정보가 없습니다.
    </div>
</template>