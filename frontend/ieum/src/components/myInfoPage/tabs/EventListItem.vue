<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ event: Object})
    const emit = defineEmits();
</script>

<template>
    <div class="eventWrapper">
        <div class="eventInfo">
            <div>
                {{ event.name }}
            </div>
            <div>
                {{ event.date }}
            </div>
        </div>
    </div>
</template>

<style>
    .eventWrapper {
        border: 1px solid;
        width: 70%;
        margin: 0 auto;
    }

    .eventInfo {
        display: flex;
        flex-direction: row;
        width: 100%;
        justify-content: space-between;
    }
</style>