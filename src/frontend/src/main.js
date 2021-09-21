import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import vuetify from "./plugins/vuetify";
import axios from "./plugins/axios"

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  axios,
  render: (h) => h(App),
}).$mount("#app");
