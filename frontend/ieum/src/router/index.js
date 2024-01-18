import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChatView from '../views/ChatView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      //메인화면
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      //대화목록 상세
      path: '/chat',
      name: 'chat',
      component: ChatView
    }
  ]
})

export default router
