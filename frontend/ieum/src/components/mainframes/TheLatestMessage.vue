<!-- <template>
  <div class="container">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">최근 대화 목록</a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
          <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">알람 등록</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                알람 종류
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">끼니 알람</a></li>
                <li><a class="dropdown-item" href="#">투약 정보 알람</a></li>
                <li><a class="dropdown-item" href="#">취침 정보 알람</a></li>
                <li><a class="dropdown-item" href="#">기념일 정보 알람</a></li>
              </ul>
            </li>
            <div></div>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">> 모든 대화</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template> -->

<template>
  <div class="container gap-0 row">
    <div class="p-2 col-10">
      <span>최근 대화 목록</span>
      <button>알람 등록</button>
      <div class="dropdown">
        <a
          class="nav-link dropdown-toggle"
          href="#"
          role="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          알람 종류
        </a>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">끼니 알람</a></li>
          <li><a class="dropdown-item" href="#">투약 정보 알람</a></li>
          <li><a class="dropdown-item" href="#">취침 정보 알람</a></li>
          <li><a class="dropdown-item" href="#">기념일 정보 알람</a></li>
        </ul>
      </div>
    </div>
    <div class="p-2 col-2">>모든 대화</div>
  </div>
</template>
<script></script>

<style scoped>
.container {
  margin: 2%;
}
</style>
