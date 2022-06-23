9<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand>
        <img @click="redirectToHome()" height="30" alt="Atos logo" src='../atos.png'/>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
            <b-nav-item @click="redirectToHome()">
              <font-awesome-icon icon="home" /> Home
            </b-nav-item>
        </b-navbar-nav>
        <b-button v-b-toggle.sidebar-border>
            Competenties
          </b-button>
          <b-sidebar id="sidebar-border">
            <div class="px-3 py-2">
              <b-card no-body header="Competenties">
                <b-list-group flush>
                  <router-link to="/competence/1">
                    <b-list-group-item>JAVA</b-list-group-item>
                  </router-link>
                  <router-link to="/competence/2">
                    <b-list-group-item>.NET</b-list-group-item>
                  </router-link>
                  <router-link to="/competence/3">
                    <b-list-group-item>Business Analyse</b-list-group-item>
                  </router-link>
                </b-list-group>
              </b-card>
            </div>
          </b-sidebar>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
            <b-button v-if="!isLoggedIn" @click="login()">
              <font-awesome-icon icon="sign-in-alt" /> Login</b-button>
            <b-button @click="signup()">
              <font-awesome-icon icon="user-plus" /> Sign up
            </b-button>
          <b-nav-item-dropdown v-show="isLoggedIn" right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <b-button><font-awesome-icon icon="user"/> {{userName}}</b-button>
            </template>
            <b-dropdown-item v-show="isAdmin" href="/admin">Admin</b-dropdown-item>
            <b-dropdown-item @click="logout()">
              <font-awesome-icon icon="sign-out-alt" /> Sign Out
            </b-dropdown-item>
          </b-nav-item-dropdown>
          <!-- <b-nav-form>
            <b-form-input
              size="sm"
              class="mr-sm-2"
              placeholder="Search"
            ></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit"
              >Search</b-button
            >
          </b-nav-form> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';

Vue.use(VueAxios, axios);

export default {
  name: 'Navbar',
  data() {
    return {
      username: null,
    };
  },
  props: {
    isAdmin: Boolean,
    isLoggedIn: Boolean,
    userName: String,
  },
  computed: {
    msg() {
      return this.$store.state.auth.msg;
    },
  },
  methods: {
    redirectToHome() {
      this.$router.push({ path: '/home' });
    },
    signup() {
      this.$router.push({ path: '/signup' });
    },
    login() {
      this.$router.push('/login');
    },
    logout() {
      this.$store.dispatch('logout');
      this.$toasted.show(`${this.userName} is ${this.msg}`).goAway(1500);
      this.login();
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
.b-dropdown-item {
  margin-inline: 2;
}
.navbar.navbar-dark.bg-dark {
  background-color: #0066a1 !important;
}

.navbar-dark .navbar-nav .nav-link {
  color: white !important;
}

.bg-primary {
  background-color: red !important;
}

.btn-secondary {
  color: #fff;
  background-color: #0066a1;
  border-color: #0066a1;
}

.btn-secondary:focus {
  color: #fff;
  background-color: #0066a1;
  border-color: #0066a1;
}

.btn-secondary:hover {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:hover:focus {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:active:focus {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

.btn-secondary:active {
  color: #fff;
  background-color: #01446b !important;
  border-color: #01446b !important;
}

</style>
