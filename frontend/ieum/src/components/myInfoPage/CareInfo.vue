<script setup>
    import 복용약 from './tabs/PillInfo.vue';
    import 기념일 from './tabs/EventInfo.vue';
    import 일정 from './tabs/ScheduleInfo.vue';
    
    import { getCareInfo } from '@/api/careInfoModify';
    import { ref, onMounted } from 'vue';

    const components = ref({복용약, 기념일, 일정});
    const currentTab = ref(components.value.복용약);
    const tabs = ref(['복용약', '기념일', '일정']);
    const imgUrl = ref('https://i10a303.p.ssafy.io/images/');

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
                if (response.data.data.careImage) {
                    careInfo.value.careImage = imgUrl.value + response.data.data.careImage;
                }
            }
        );
        console.log("불러오기 완료");
    }); 
</script>

<template>
    <div class="careInfo" v-if="careInfo.careName">
        <div class="profile">
            <div v-if="careInfo.careImage">
                <img class="profileImage" :src="careInfo.careImage">
            </div>
            <div v-else-if="careInfo.careGender === 'FEMALE'">
                <img class="profileImage" src="@/assets/images/old-woman.png">
            </div>
            <div v-else>
                <img class="profileImage" src="@/assets/images/old-man.png">
            </div>
        </div>
        <div class="info">
            <div class="nameInfo">
                <div class="text-xs">
                    {{ careInfo.careName }} &nbsp;
                </div>
                <div v-if="careInfo.careGender === 'FEMALE'" class="text-xs">
                    할머니
                </div>
                <div v-else class="text-xs">
                    할아버지
                </div>
            </div>
            <p class="text-xs"> {{ careInfo.careAddr }}</p>
            <p class="text-xs"> {{ careInfo.carePhone }}</p>
            <p class="text-xs"> {{ careInfo.careSerial }} </p>
        </div>
        <div>
            <router-link :to="{name : 'TheCareInfoModifyView'}">
                <img class="modify-btn w-6 h-6" src="@/assets/images/modify.png" />
            </router-link>
        </div>

    </div>
    <div v-else class="noInfo">
        등록된 보호자 정보가 없습니다
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
    .modifyBtn {
        margin-right: 3%;
    }

    .noInfo {
        margin: 10% auto;
        text-align: center;
    }

    .careInfo {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        margin: 3% auto;
        width: 80%;
    }

    .profileImage {
        max-width: 100%;
        max-height: 100%;
    }
    
    .nameInfo {
        display: flex;
        flex-direction: row;
    }

    .profile {
        width: 40%;
        max-height: 100%;
    }

    .info {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
        width: 60%;
        margin-left: 5%;
        font-size: 130%;
    }

    .components {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .buttons {
        display: flex;
        justify-content: space-evenly;
        width: 80%;
        margin: 7% auto 2% auto;
    }

    .tab-button {
        width: 80%;
    }

    .buttons button {
        opacity: 0.5;
    }

    .buttons button.active {
        /* 선택된 버튼 */
        opacity: 1;
        background-color: #EBFBF7;
        color: #00725E;
    }
</style>
