import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/auth",
    name: "Auth",
    component: () =>
      import("../components/Auth.vue"),
  },
  {
    path: "/registration",
    name: "Registration",
    component: () =>
      import("../views/Registration.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
