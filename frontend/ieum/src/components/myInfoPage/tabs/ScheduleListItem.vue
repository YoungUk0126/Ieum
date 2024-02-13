<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ schedule: Object})
    const emit = defineEmits();
</script>

<template>
    <div style="width: 80%;">
        <div class="scheduleWrapper">
            <div class="scheduleName">
                {{ schedule.eventName }}
            </div>
            <div class="date">
                <div>
                    {{ schedule.date }}
                </div>
                <div>
                    {{ schedule.time }}
                </div>
            </div>
        </div>
    </div>
    
</template>

<style>
    .scheduleWrapper {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        border-radius: 20px;
        margin: 0.5% auto;
        padding: 1.3% 3%;
        background-color: #f1f3f5
    }

    .scheduleName {
        margin: auto 0;
    }

    .date {
        display: flex;
        flex-direction: column;
        text-align: end;
    }
</style>