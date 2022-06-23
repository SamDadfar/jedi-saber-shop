import Vue from 'vue';
import Router from 'vue-router';
import Homepage from '@/components/view/HomePage.vue';
import Competence from '@/components/view/CompetenceTemplate.vue';
import Calandar from '@/components/view/Calendar.vue';
import Signup from '@/components/view/Signup.vue';
import Login from '@/components/view/Login.vue';
import Profile from '@/components/view/Profile.vue';
import Admin from '@/components/view/Admin.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
  routes: [
    { path: '/home', component: Homepage, meta: { requiresAuth: true } },
    { path: '/', component: Homepage },
    { path: '/competence/:id', component: Competence, meta: { requiresAuth: true } },
    { path: '/calendar', component: Calandar, meta: { requiresAuth: true } },
    { path: '*', redirect: '/' },
    { path: '/login', component: Login },
    { path: '/signup', component: Signup },
    { path: '/profile', component: Profile, meta: { requiresAuth: true } },
    { path: '/admin', component: Admin, meta: { requiresAuth: true, role: 'ADMIN' } },
  ],
});

router.beforeEach((to, from, next) => {
  const data = JSON.parse(localStorage.getItem('data'));
  const user = data ? data.user : { roles: [] };
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page;
    if (!data) {
      next('/login');
    }
    switch (to.meta.role) {
      case 'ADMIN':
        if (user.roles.includes('ADMIN')) {
          next();
        } else {
          next('/');
        }
        break;
      default:
        break;
    }
    next();
  } else {
    next(); // make sure to always call next()!
  }
});

export default router;
