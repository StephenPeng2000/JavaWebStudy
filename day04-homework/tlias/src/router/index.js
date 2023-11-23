import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/emp',
    name: 'emp',
    component: () => import( '../views/EmpView.vue')
  },
  {
    path: '/dept',
    name: 'dept',
    component: () => import('../views/DeptView.vue')
  },
  {
    path: '/',
      //重定向
    redirect: '/emp'
  }
]

const router = new VueRouter({
  routes
})

export default router
