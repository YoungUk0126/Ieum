import { createRouter, createWebHistory, useRouter } from 'vue-router'
import VueCookies from 'vue-cookies'
import swal from 'sweetalert'

import TheMainViewVue from '@/views/TheMainView.vue'
import TheRegisterViewVue from '@/views/TheRegisterView.vue'
import TheCallViewVue from '@/views/TheCallView.vue'
import TheChatViewVue from '@/views/TheChatView.vue'
import TheLoginViewVue from '@/views/TheLoginView.vue'
import TheCareInfoModifyViewVue from '@/views/TheCareInfoModifyView.vue'
import NotFound from '@/error/NotFound.vue'
import TheFindInfoView from '@/views/TheFindInfoView.vue'
import TheMemberInfoViewVue from '@/views/TheMemberInfoView.vue'
import TheCareInfoViewVue from '@/views/TheCareInfoView.vue'
import TheAlarmViewVue from '@/views/TheAlarmView.vue'
import TheCareCallViewVue from '@/views/TheCareCallView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'TheMainViewVue',
      component: TheMainViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      //회원가입 페이지
      path: '/register',
      name: 'TheRegisterView',
      component: TheRegisterViewVue
    },
    {
      //로그인 페이지
      path: '/login',
      name: 'TheLoginView',
      component: TheLoginViewVue
    },
    {
      //화상통화 페이지
      path: '/call',
      name: 'TheCallView',
      component: TheCallViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      path: '/chat',
      name: 'TheChatView',
      component: TheChatViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      //정보수정페이지
      path: '/careInfoModify',
      name: 'TheCareInfoModifyView',
      component: TheCareInfoModifyViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      path: '/careInfo',
      name: 'TheCareInfo',
      component: TheCareInfoViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      path: '/findInfo',
      name: 'TheFindInfoView',
      component: TheFindInfoView,
      children: [
        {
          path: '',
          name: 'TheInputInfo',
          component: () => import('@/components/findInfo/TheInputInfo.vue')
        },
        {
          path: '/secondStep',
          name: 'TheAuthInfo',
          component: () => import('@/components/findInfo/TheEditInfo.vue')
        },
        {
          path: '/endStep',
          name: 'TheEndInfo',
          component: () => import('@/components/findInfo/TheEndInfo.vue')
        }
      ]
    },
    {
      path: '/chat',
      name: 'TheChatView',
      component: TheChatViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      path: '/memberInfo',
      name: 'TheMemberInfoView',
      component: TheMemberInfoViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      //화상통화 페이지
      path: '/callcare',
      name: 'TheCareCallViewVue',
      component: TheCareCallViewVue
    },
    {
      path: '/alarm',
      name: 'TheAlarmView',
      component: TheAlarmViewVue,
      meta: { requiresAuth: true } // 세션 검증 할 것인지
    },
    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/404'
    }
  ]
})

// 세션 확인 작업
router.beforeEach((to, from, next) => {
  // 여기에 전역 필터링 로직을 추가
  // 예: 로그인이 필요한 페이지에 대한 필터
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // 사용자가 로그인되어 있지 않다면 로그인 페이지로 리디렉션
    if (!VueCookies.get('auth')) {
      swal({
        title: '로그인',
        text: '로그인 정보가 존재하지 않습니다',
        icon: 'error',
        buttons: {
          confirm: {
            text: '확인',
            visible: true,
            className: '',
            closeModal: true
          }
        }
      }).then(() => {
        VueCookies.remove('accessToken')
        VueCookies.remove('refreshToken')
        VueCookies.remove('auth')

        next({ path: '/login' })
      })
    } else {
      next()
    }
  } else {
    // 필터링이 필요하지 않은 경우 그냥 계속 진행
    next()
  }
})

export default router
