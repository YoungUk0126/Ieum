<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ pill: Object})
    const emit = defineEmits();

</script>

<template>
    <div class="pillWrapper">
        <div class="pillInfo">
            <div>
                {{  pill.name }}
            </div>
            <div>
                {{ pill.period }}
            </div>
        </div>
        <div class="pillInfo">
            <div>
                {{ pill.week.join(" ") }}
            </div>
            <div>
                {{  pill.timing }}
            </div>
        </div>
    </div>
</template>

<style>
    .pillWrapper {
        display: flex;
        flex-direction: column;
        border: 1px solid;
        margin: 0 auto;
        width: 70%;
    }

    .pillInfo {
        display: flex;
        flex-direction: row;
        width: 100%;
        justify-content: space-between;
    }
</style>