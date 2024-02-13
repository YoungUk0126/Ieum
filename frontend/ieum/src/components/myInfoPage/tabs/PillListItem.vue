<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ pill: Object})
    const emit = defineEmits(['delete']);

    const onDelete = () => {
        emit('delete', props.pill.pillInfoNo)
    }
</script>

<template>
    <div class="pillWrapper p-3">
        <div class="pillInfo gap-2" >
            <div>
                {{  pill.pillName }}
            </div>
            <div class="text-xs">
                {{ pill.pillDate }}
            </div>
        </div>
        <div class="pillInfo pillDate gap-2">
            <div class="text-xs">
                {{ pill.pillStartDate }} ~ {{ pill.pillEndDate }}
            </div>
            <div class="text-s">
                {{  pill.pillMethod }}
            </div>
        </div>
        <div class="bin w-6 h-6" @click="onDelete">
            <img src="@/assets/images/trash_icon.png" />
        </div>
    </div>
</template>

<style>
    .bin {
        display: flex;
        align-items: center;
        margin: auto 0;
    }

    .pillWrapper {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        /* border: 1px solid; */
        border-radius: 20px;
        margin: 0.5% auto;
        /* padding: 1.3% 3%; */
        width: 80%;
        background-color: #f1f3f5;
    }

    .pillDate {
        text-align: end;
        flex:0.9
    }

    .pillInfo {
        display: flex;
        flex-direction: column;
        /* flex: 0.5; */
        /* width: 100%; */
        /* justify-content: space-between; */
    }
</style>