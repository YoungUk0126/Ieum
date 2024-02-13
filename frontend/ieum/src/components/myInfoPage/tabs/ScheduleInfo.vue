<script setup>
    import ScheduleListItem from './ScheduleListItem.vue';
    import { getAllList } from '@/api/schedule';
    import { ref, onMounted } from "vue";

    const scheduleInfoList = ref([]);

    onMounted(() => {
        // 추후에 api 조회 호출
        console.log("Schedule Info onMounted");
        getAllList(
        (response) => {
            console.log("불러온 값", response.data.data);
            scheduleInfoList.value = response.data.data;
        }, (error) => {
            console.log("Schedule Info 불러오던 중 Error 발생!!");
        })
    });
</script>

<template>
    <div v-if="scheduleInfoList.length!=0">
        <ScheduleListItem v-for="scheduleInfo in scheduleInfoList" :key="scheduleInfo.id" :schedule="scheduleInfo"></ScheduleListItem>
    </div>
    <div v-else>
        등록된 일정 정보가 없습니다.
    </div>
</template>