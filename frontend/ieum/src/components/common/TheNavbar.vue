<script setup></script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <router-link to="/main"><a class="navbar-brand" href="#">Navbar</a></router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/chat"
              ><a class="nav-link active" aria-current="page" href="#">대화목록</a></router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/info">
              <a class="nav-link" href="#">부모님 정보</a>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/schedule">
              <a class="nav-link" href="#">일정</a>
            </router-link>
          </li>
        </ul>

        <!-- Use ms-auto to move the button group to the right -->
        <div class="ms-auto">
          <div class="d-grid gap-6 d-flex">
            <div class="card">
              <button class="btn btn-primary me-md-2" type="button">Button</button>
              <button class="btn btn-primary" type="button">Button</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background-color: rgb(235, 251, 247) !important;
}
</style>
