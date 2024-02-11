<script setup>
    import EventList from './EventListItem.vue';
    import { ref, onMounted } from "vue";

    const eventInfoList = ref([
        { id:1, name:"첫째 아들 생일", date:"02-10" },
        { id:2, name:"둘쨰 아들 생일", date:"02-11" },
        { id:3, name:"셋째 아들 생일", date:"02-12" },
        { id:4, name:"넷째 아들 생일", date:"02-13" },
        { id:5, name:"첫째 딸 생일", date:"02-14" },
        { id:6, name:"둘째 딸 생일", date:"02-15" },
        { id:7, name:"셋째 딸 생일", date:"02-16" },
        { id:8, name:"넷째 딸 생일", date:"02-17" },
    ]);

    onMounted(() => {
    // 추후에 api 조회 호출
    console.log("event Info onMounted");
    });
</script>

<template>
    <div>
        <EventList v-for="eventInfo in eventInfoList" :key="eventInfo.id" :event="eventInfo"></EventList>
    </div>
</template>