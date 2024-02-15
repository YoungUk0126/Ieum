import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';

import App from './App.vue'
import router from './router'
import './styles/app.css'
import 'flowbite/dist/flowbite.min.css'
import 'flowbite/dist/flowbite.min.js'
import 'v-calendar/style.css'
import '@/font/fonts.css'

// cookie
import VueCookies from 'vue-cookies'
VueCookies.config('14d')

const app = createApp(App)

// calendar
app.use(setupCalendar, {})

app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)


app.use(VueCookies)
app.use(createPinia())
app.use(router)
app.mount('#app')


app.config.productionTip = false
