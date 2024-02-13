<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ event: Object})
    const emit = defineEmits(['delete']);

    const onDelete = () => {
        emit('delete', props.event.eventNo)
    }
</script>

<template>
    <div class="eventWrapper p-3">
        <div class="eventInfo">
            <div class="eventName">
                {{ event.eventName }}
            </div>
            <div class="eventDate">
                {{ event.eventDate }}
            </div>
            <div class="bin w-6 h-6" @click="onDelete">
                <img src="@/assets/images/trash_icon.png" />
            </div>
        </div>
    </div>
</template>

<style>
    .bin {
        display: flex;
        align-items: center;
        margin: auto 0;
    }

    .eventName {
        flex: 0.9; /* 자동으로 남은 공간을 채웁니다.*/
    }

    .eventWrapper {
        display: flex;
        flex-direction: column;
        /* border: 1px solid; */
        border-radius: 20px;
        margin: 0.5% auto;
        /* padding: 1.3% 3%; */
        width: 80%;
        background-color: #f1f3f5;
    }

    .eventInfo {
        display: flex;
        flex-direction: row;
        /* width: 100%; */
        justify-content: space-between;
    }
</style>