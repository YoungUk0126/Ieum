<template>
  <!-- Button trigger modal -->
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#conv">
    Launch demo modal
  </button>

  <!-- Modal -->
  <div class="modal fade" id="conv" tabindex="-1" aria-labelledby="conv" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">...</div>
        <div class="modal-footer">
          <button
            v-bind="closeBtn"
            type="button"
            id="btnClose"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn btn-primary" @click="closeModal">Save changes</button>
        </div>
      </div>
    </div>
  </div>
  <button v-bind="closeBtn" type="button" id="btnClose" class="" data-bs-dismiss="modal"></button>
</template>

<script setup>
import { ref } from 'vue'
const props = defineProps(['modalId'])

const modalId = ref(props.modalId)
const closeBtn = ref()

const closeModal = () => {
  console.log(document.getElementById('btnClose'))
  document.getElementById('btnClose').click()
}

console.log(modalId.value)
</script>

<style scoped></style>
