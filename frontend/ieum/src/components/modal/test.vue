<template>
  
    <!-- Modal toggle -->
    <button
      :data-modal-target="1"
      data-modal-toggle="default-modal"
      class="menu-block text-black rounded md:bg-transparent md:text-green-500 md:p-0 md:hover:text-green-600"
      type="button"
    >
      일정
    </button>
  
    <!-- Modal -->
    <div
      class="modal fade modal-lg"
      id="1"
      tabindex="-1"
     aria-labelledby="1"
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
            <!-- <VModalAnniBody :modalId="modalId"></VModalAnniBody>
            <VModalMeal :modalId="modalId"></VModalMeal> -->
            <VModalWakeandSleep></VModalWakeandSleep>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              id="btnClose"
              class="btn close-btn"
              data-bs-dismiss="modal"
              display: hidden
            >
              Close
            </button>
            <button type="button" class="btn btn-outline-secondary" @click="closeModal">등록</button>
          </div>
        </div>
      </div>
    </div>
  </template>