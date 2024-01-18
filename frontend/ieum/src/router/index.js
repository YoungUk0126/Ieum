// import TheChatViewVue from '@/views/TheChatView.vue'
import TheMainViewVue from '@/views/TheMainView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // 메인화면
      path: '/',
      name: 'TheMainView',
      component: TheMainViewVue
    }
    // {
    //   //로그인화면
    //   path: '/login',
    //   name: 'TheLoginView',
    //   component: TheLoginView
    // },
    // {
    //   //내정보화면
    //   path: '/mypage',
    //   name: 'TheLoginView',
    //   component: TheMyPageView
    // },
    // {
    //   //채팅내역
    //   path:'/chat',
    //   name: 'TheChatView',
    //   component: TheChatView
    // },   
    // {
    //   //회원가입
    //   path: '/register',
    //   name: 'TheRegisterView',
    //   component: TheBoardView,
    //   children: [
    //     {
    //       path: 'list',
    //       name: 'BoardList',
    //       component: () => import('@/components/board/BoardList.vue')
    //     },
    //     {
    //       path: 'detail/:articleNo',
    //       name: 'BoardDetail',
    //       component: () => import('@/components/board/BoardDetail.vue')
    //     }
    //   ]
    // },
  ]
})

export default router
