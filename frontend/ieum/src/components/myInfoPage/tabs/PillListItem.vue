<script setup>
    import { defineProps, defineEmits } from 'vue';

    const props = defineProps({ pill: Object})
    const emit = defineEmits(['delete']);

    const onDelete = () => {
        emit('delete', props.pill.pillInfoNo)
    }
</script>

<template>
    <div class="pillWrapper">
        <div class="pillInfo">
            <div>
                {{  pill.pillName }}
            </div>
            <div>
                {{ pill.pillDate }}
            </div>
        </div>
        <div class="pillInfo pillDate">
            <div>
                {{ pill.pillStartDate }} ~ {{ pill.pillEndDate }}
            </div>
            <div>
                {{  pill.pillMethod }}
            </div>
        </div>
        <div class="bin" @click="onDelete">
            <img src="@/assets/images/trash_icon.png" />
        </div>
    </div>
</template>

<style>
    .bin {
        display: flex;
        width: 5%;
        height: 5%;
        margin: auto 0 auto 1%;
        padding-left: 1%;
    }

    .pillWrapper {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        /* border: 1px solid; */
        border-radius: 20px;
        margin: 0.5% auto;
        padding: 1.3% 3%;
        width: 80%;
        height: 30%;
        background-color: #f1f3f5;
    }

    .pillDate {
        text-align: end;
        flex:1%
    }

    .pillInfo {
        display: flex;
        flex-direction: column;
        /* width: 100%; */
        justify-content: space-between;
    }
</style>