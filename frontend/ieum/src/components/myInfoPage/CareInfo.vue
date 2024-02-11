<script>
import Pill from './tabs/PillInfo.vue';
import Event from './tabs/EventInfo.vue';
import Schedule from './tabs/ScheduleInfo.vue';

export default {
    components: {
        Pill,
        Event,
        Schedule
    },
    data() {
        return {
            currentTab: 'Pill',
            tabs: ['Pill', 'Event', 'Schedule']
        }
    }
}
</script>

<template>
    <div class="tabs">
        <div class="buttons">
            <button v-for="tab in tabs" :key="tab" 
                    :class="['tab-button', {active : currentTab == tab}]"
                    @click="currentTab = tab">
                {{  tab }}    
            </button>
        </div>
        <component class="components" :is="currentTab"></component>
    </div>
</template>

<style>
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