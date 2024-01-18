import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    /*{
      path: '/',
      name: 'TheMainView',
      component: TheMainView
    },
     자식 페이지 라우팅 예시
    {
      path: '/spot',
      name: 'TheSpotMapView',
      component: TheSpotMapView
    },
    {
      path: '/board',
      name: 'TheBoardView',
      component: TheBoardView,
      children: [
        {
          path: 'list',
          name: 'BoardList',
          component: () => import('@/components/board/BoardList.vue')
        },
        {
          path: 'detail/:articleNo',
          name: 'BoardDetail',
          component: () => import('@/components/board/BoardDetail.vue')
        }
      ]
    },*/
  ]
})

export default router
