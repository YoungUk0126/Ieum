<script setup></script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/"
        ><img class="logo-img" src="@/assets/images/logo.png" alt=""
      /></a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdowns"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse router pe-5">
        <ul class="navbar-nav justify-content-between">
          <div class="left-elem-box">
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
            <li class="nav-item">
              <router-link to="/login">
                <a class="nav-link" href="#">로그아웃</a>
              </router-link>
            </li>
          </div>
          <!-- Use ms-auto to move the button group to the right -->
          <div class="right-elem-box ms-auto">
            <div class="d-flex my-info-button rounded-pill h-25">
              <img class="profile-img rounded-circle" src="@/assets/images/영욱이.png" />

              <a class="btn me-md-2 my-info-text" href="@/views/TheInfoView.vue">내 정보</a>
            </div>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  background-color: rgb(235, 251, 247) !important;
}

.router {
  height: 6rem;
}

.navbar-brand {
  text-align: center;
}

.navbar-nav {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.left-elem-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.right-elem-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: end;
}

.my-info-button {
  padding: 5%;
  text-decoration-color: white;
  background-color: #33a38f;
  justify-content: space-between;
}

.my-info-text {
  color: white;
  align-self: center;
  font-family: Work Sans;
  font-weight: 700;
}

.profile-img {
  height: 100%;
  max-width: 60%;
}

.logo-img {
  width: 50%;
  object-fit: cover;
}
</style>
