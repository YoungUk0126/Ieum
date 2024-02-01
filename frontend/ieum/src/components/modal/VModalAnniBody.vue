<template>
  <div class="content-container">
    <div class="row">
      <div class="title-input mb-4">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 입력해주세요"
          aria-label="Username"
          aria-describedby="basic-addon1"
        />
      </div>
      <div class="calendar row mb-4">
        <div class="start-date col-6">
          <label for="start">날짜 : </label>

          <input
            type="date"
            id="start"
            name="trip-start"
            value="2025-01-01"
            min="2018-01-01"
            max="2100-12-31"
          />
        </div>

        <div class="checkbox-box col-4">
          <input type="checkbox" class="btn btn-check" id="btncheck1" autocomplete="off" />
          <label class="btn btn-outline-secondary" for="btncheck1">매년 반복</label>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.btn {
  border: 1px black solid;
}
.modal-dialog {
  width: 40%;
}
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

.close-btn {
  display: none;
}

.title-input {
  width: 60%;
}
.register-button.font {
  color: #f5f5f5;
  font-size: 25px;
  font-family: Inter;
  font-weight: 700;
  word-wrap: break-word;
}

.calendar-box {
  width: 60%;
  height: 100%;
  margin-left: 3%;
  padding: 0;
  background: white;
  border-radius: 3px;
  border: 1px rgba(0, 0, 0, 0.1) solid;
}
</style>
