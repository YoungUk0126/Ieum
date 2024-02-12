<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ event: Object})
    const emit = defineEmits();
</script>

<template>
    <div class="eventWrapper">
        <div class="eventInfo">
            <div>
                {{ event.eventName }}
            </div>
            <div>
                {{ event.eventDate }}
            </div>
        </div>
    </div>
</template>

<style>
    .eventWrapper {
        display: flex;
        flex-direction: column;
        /* border: 1px solid; */
        border-radius: 20px;
        margin: 0.5% auto;
        padding: 1.3% 3%;
        width: 80%;
        background-color: #f1f3f5;
    }

    .eventInfo {
        display: flex;
        flex-direction: row;
        width: 100%;
        justify-content: space-between;
    }
</style>