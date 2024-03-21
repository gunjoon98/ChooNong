import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config';
import ConfirmationService from 'primevue/confirmationservice';
import 'primevue/resources/themes/aura-light-green/theme.css'
import AnimateOnScroll from 'primevue/animateonscroll';

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(PrimeVue);
app.use(ConfirmationService);
app.directive('animateonscroll', AnimateOnScroll);

app.mount('#app')
