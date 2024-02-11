<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ schedule: Object})
    const emit = defineEmits();
</script>

<template>
    <div style="width: 70%;">
        <div class="scheduleWrapper">
            <div class="scheduleName">
                {{ schedule.eventName }}
            </div>
            <div class="date">
                <div>
                    {{ schedule.eventDate }}
                </div>
                <div>
                    시간은 추가필요
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
        border: 1px solid;
        /* width: 70%; */
        margin: 0 auto;
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