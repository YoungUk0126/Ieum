<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ schedule: Object})
    const emit = defineEmits(['delete']);

    const onDelete = () => {
        emit('delete', props.schedule.eventNo)
    }
</script>

<template>
    <div style="width: 80%;">
        <div class="scheduleWrapper p-3">
            <div class="scheduleName">
                {{ schedule.eventName }}
            </div>
            <div class="date gap-1">
                <div class="text-s">
                    {{ schedule.date }}
                </div>
                <div class="text-xs">
                    {{ schedule.time }}
                </div>
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
    }

    .scheduleWrapper {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        border-radius: 20px;
        margin: 0.5% auto;
        background-color: #f1f3f5
    }

    .scheduleName {
        margin: auto 0;
        flex: 0.9;
    }

    .date {
        display: flex;
        flex-direction: column;
        text-align: end;
    }
</style>