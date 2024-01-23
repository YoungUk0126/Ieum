import TheMainViewVue from '@/views/TheMainView.vue'
import TheRegisterViewVue from '@/views/TheRegisterView.vue'
import TheCallViewVue from '@/views/TheCallView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'TheMainView',
      component: TheMainViewVue
    },
    {
      //회원가입 페이지
      path: '/register',
      name: 'TheRegisterView',
      component: TheRegisterViewVue
    },
    {
      //화상통화 페이지
      path: '/call',
      name: 'TheCallView',
      component: TheCallViewVue
    }
    // 자식 페이지 라우팅 예시
    // {
    //   path: '/',
    //   name: 'TheSpotMapView',
    //   component:
    // },
    // {
    //   path: '/board',
    //   name: 'TheBoardView',
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
