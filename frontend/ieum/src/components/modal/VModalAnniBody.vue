<template>
  <div class="content-container">
    <div class="row">
      <div class="dropdown mb-4">
        <button
          class="btn dropdown-toggle"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          알람 종류
        </button>
        <ul class="dropdown-menu">
          <li>
            <div class="dropdown-item" dropdownId="anniv">기념일</div>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="wakeandsleep">기상/취침</a>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="injection">투약</a>
          </li>
          <li>
            <a class="dropdown-item" dropdownId="meal">식사</a>
          </li>
        </ul>
      </div>
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
        <div class="calendar-box col-6">
          <img src="@/assets/images/달력버튼.png" />
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

<style lang="scss" scoped></style>
