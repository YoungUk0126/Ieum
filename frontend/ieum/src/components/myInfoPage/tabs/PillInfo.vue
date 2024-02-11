<script setup>
import PillList from './PillListItem.vue';
import { ref, onMounted } from "vue";

const pillInfoList = ref([
    { id:1, name:"당뇨약1", week: ['월', '화', '수'], period: "24.02.29~24.03.08", timing:"식전"},
    { id:2, name:"당뇨약2", week: ['월', '화', '수', '목', '금', '토', '일'], period: "24.02.29~24.03.08", timing:"식후"},
    { id:3, name:"당뇨약3", week: ['월'], period: "24.02.29~24.03.08", timing:"식전"},
    { id:4, name:"당뇨약4", week: ['월', '화'], period: "24.02.29~24.03.08", timing:"식전"},
    { id:5, name:"당뇨약5", week: ['월', '수', '금'], period: "24.02.29~24.03.08", timing:"식후"},
    { id:6, name:"당뇨약6", week: ['월', '화', '목', '금'], period: "24.02.29~24.03.08", timing:"식전"},
    { id:7, name:"당뇨약7", week: ['월', '수', '금', '토', '일'], period: "24.02.29~24.03.08", timing:"식후"},
]);

onMounted(() => {
    // 추후에 api 조회 호출
    console.log("pill Info onMounted");
});
</script>

<template>
    <div>
        <PillList v-for="pillInfo in pillInfoList" :key="pillInfo.id" :pill="pillInfo"></PillList>
    </div>
</template>
