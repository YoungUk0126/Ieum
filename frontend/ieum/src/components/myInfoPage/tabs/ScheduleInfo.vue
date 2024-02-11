<script setup>
    import ScheduleListItem from './ScheduleListItem.vue';
    import { ref, onMounted } from "vue";

    const scheduleInfoList = ref([
        { id:1, name:"경로당가기1", date:"02-10", time:"AM 09:00"},
        { id:2, name:"경로당가기2", date:"02-11", time:"AM 10:00" },
        { id:3, name:"경로당가기3", date:"02-10", time:"AM 09:00"},
        { id:4, name:"경로당가기4", date:"02-12", time:"AM 11:00" },
        { id:5, name:"경로당가기5", date:"02-13", time:"AM 09:00"},
        { id:6, name:"경로당가기6", date:"02-15", time:"AM 10:00" },
        { id:7, name:"경로당가기7", date:"02-16", time:"AM 09:00"},
        { id:8, name:"경로당가기8", date:"02-18", time:"AM 10:00" }
    ]);

    onMounted(() => {
    // 추후에 api 조회 호출
    console.log("event Info onMounted");
    });
</script>

<template>
    <div>
        <ScheduleListItem v-for="scheduleInfo in scheduleInfoList" :key="scheduleInfo.id" :schedule="scheduleInfo"></ScheduleListItem>
    </div>
    
</template>