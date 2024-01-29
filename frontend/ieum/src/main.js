import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import './styles/app.css'
import 'flowbite/dist/flowbite.min.css'
import 'flowbite/dist/flowbite.min.js'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.mount('#app')
