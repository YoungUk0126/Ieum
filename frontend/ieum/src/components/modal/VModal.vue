<template>
  <!-- Button trigger modal -->
  <button
    type="button"
    class="alarm-btn"
    data-bs-toggle="modal"
    v-bind:data-bs-target="'#' + modalId"
  >
    알람 등록
  </button>

  <!-- Modal -->
  <div
    class="modal fade modal-lg"
    :id="modalId"
    tabindex="-1"
    v-bind:aria-labelledby="modalId"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"></h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <VModalAnniBody :modalId="modalId"></VModalAnniBody>
        </div>
        <div class="modal-footer">
          <button
            v-bind="closeBtn"
            type="button"
            id="btnClose"
            class="btn close-btn"
            data-bs-dismiss="modal"
            display: hidden
          >
            Close
          </button>
          <button type="button" class="register-button" @click="closeModal">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import VModalAnniBody from './VModalAnniBody.vue'

const modalId = ref('anniv')

const closeBtn = ref()

const closeModal = () => {
  console.log(document.getElementById('btnClose'))
  document.getElementById('btnClose').click()
}


</script>

<style scoped>
.alarm-btn,
.register-button {
  padding-top: 6px;
  padding-bottom: 5.11px;
  padding-left: 20px;
  padding-right: 20px;
  background: #33a38f;
  border-radius: 10px;
  overflow: hidden;
  border: 1px #1de4c1 solid;
  justify-content: center;
  align-items: center;
  display: inline-flex;
  color: white;
  font-size: 16px;
  font-family: Work Sans;
  font-weight: 700;
  line-height: 24px;
  word-wrap: break-word;
  margin-right: 5%;
}
</style>
