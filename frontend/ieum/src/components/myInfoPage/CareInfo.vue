<script setup>
    import Pill from './tabs/PillInfo.vue';
    import Event from './tabs/EventInfo.vue';
    import Schedule from './tabs/ScheduleInfo.vue';
    
    import { getCareInfo } from '@/api/careInfoModify';
    import { ref, onMounted } from 'vue';

    const components = ref({Pill, Event, Schedule});
    const currentTab = ref(components.value.Pill);
    const tabs = ref(['Pill', 'Event', 'Schedule']);

    const careInfo = ref({
        careName: '',
        careGender: '',
        careBirth: '',
        carePhone: '',
        careAddr: '',
        careImage: '',
        careSerial: ''
    });

    onMounted(() => {
        console.log("Care Info onMounted");
        getCareInfo(
            (response) => {
                console.log("불러온 값 ", response.data.data);
                careInfo.value = response.data.data;
                console.log("careInfo", careInfo.value);
            },
            (error) => {
                console.log("getCareInfo API 호출 중 에러 발생!");
            }
        );
        console.log("불러오기 완료");
    }); 
</script>

<template>
    <div class="careInfo" v-if="!isLoading">
        <div class="profile">
            <h2>프로필 사진</h2>
        </div>
        <div class="info">
            <p>시리얼 넘버 : {{ careInfo.careSerial }} </p>
            <p>성별 :  {{ careInfo.careGender }}</p>
            <p>주소 :  {{ careInfo.careAddr }}</p>
            <p>이름 : {{ careInfo.careName }}</p>
            <p>핸드폰 :  {{ careInfo.carePhone }}</p>
        </div>
    </div>
    <div class="tabs">
        <div class="buttons">
            <button v-for="tab in tabs" :key="tab" 
                :class="['tab-button', {active : currentTab === components[tab]}]"
                @click="currentTab = components[tab]">
                {{  tab }}      
            </button>
        </div>
        <component class="components" :is="currentTab"></component>
    </div>
</template>

<style>
    .careInfo {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        margin: 0 auto;
        width: 70%;
        height: 150px;
    }

    .info {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        width: 60%;
    }

    .components {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .buttons {
        display: flex;
        justify-content: space-evenly;
    }
    .buttons button {
        opacity: 0.5;
    }
    .buttons button.active {
        /* 선택된 버튼 */
        opacity: 1;
        background-color: aquamarine;
    }
</style>
