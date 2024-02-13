<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ pill: Object})
    const emit = defineEmits();

</script>

<template>
    <div class="pillWrapper">
        <div class="pillInfo">
            <div>
                {{  pill.pillName }}
            </div>
            <div>
                {{ pill.pillStartDate }} ~ {{ pill.pillEndDate }}
            </div>
        </div>
        <div class="pillInfo">
            <div>
                {{ pill.pillDate }}
            </div>
            <div>
                {{  pill.pillMethod }}
            </div>
        </div>
    </div>
</template>

<style>
    .pillWrapper {
        display: flex;
        flex-direction: column;
        /* border: 1px solid; */
        border-radius: 20px;
        margin: 0.5% auto;
        padding: 1.3% 3%;
        width: 80%;
        background-color: #f1f3f5;
    }

    .pillInfo {
        display: flex;
        flex-direction: row;
        width: 100%;
        justify-content: space-between;
    }
</style>