<script setup>
    import PillList from './PillListItem.vue';
    import { getAllInject } from '@/api/modalAlarms/injection';
    import { ref, onMounted } from "vue";

    const pillInfoList = ref([]);

    function convertWeekdays(numWeek) {
        const weekdays = ['월', '화', '수', '목', '금', '토', '일'];
        let result = "";

        for (let i = 0; i < numWeek.length; i++) {
            if (numWeek[i] === '1') {
                result += weekdays[i] + " ";
            }
        }

        // 불필요한 공백 제거
        return result.trim(); 
    }

    onMounted(() => {
        // 추후에 api 조회 호출
        console.log("Pill Info onMounted");
        getAllInject(
        (response) => {
            console.log("불러온 값", response.data.data);
            // 받아온 데이터를 처리하여 pillDate를 변환
            response.data.data.forEach(pill => {
                pill.pillDate = convertWeekdays(pill.pillDate);
            });
            pillInfoList.value = response.data.data;
        }, (error) => {
            console.log("Pill Info 불러오던 중 Error 발생!!");
        })
    });
</script>

<template>
    <div v-if="pillInfoList.length!=0">
        <PillList v-for="pillInfo in pillInfoList" :key="pillInfo.id" :pill="pillInfo"></PillList>
    </div>
    <div v-else>
        등록된 복용약 정보가 없습니다.
    </div>
</template>
