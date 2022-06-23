<template>
  <div class="container">
    <div class="card bg-light">
      <article class="card-body mx-auto" style="max-width: 400px;">
        <h2 class="card-title mt-3 text-center">Login</h2>
        <form v-on:submit.prevent="handleLogin" novalidate>
          <div class="form-group input-group">
            <div class="input-group-prepend">
              <span class="input-group-text"> <i class="fa fa-user"></i> </span>
            </div>
            <input
              type="text"
              class="form-control"
              id="username"
              placeholder="Username"
              v-model="user.username"
            />
          </div>
          <div class="form-group input-group">
            <div class="input-group-prepend">
              <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder="Password"
              v-model="user.password"
            />
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-primary btn-block" value="Login" />
          </div>
          <div class="form-group">
            <a @click="oauthRedirect()"  class="btn btn-block btn-social btn-google">
              <i class="fab fa-google"></i> Sign in with Google</a>
          </div>
        </form>
      </article>
    </div>
  </div>
</template>

<script>
// import Vue from 'vue';
// import axios from 'axios';
// import VueAxios from 'vue-axios';
// import { ref } from 'vue';
import User from '../../model/user';

export default {
  name: 'Login',
  data() {
    return {
      user: new User('', '', ''),
      loading: false,
      message: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    msg() {
      const { username } = this.$store.state.auth.data.user;
      return username ? `${username} ${this.$store.state.auth.msg}` : this.$store.state.auth.msg;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/home');
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      if (this.user.username && this.user.password) {
        this.$store.dispatch('login', this.user)
          .then(() => {
            this.$toasted.show(this.msg).goAway(1500);
            this.$router.push('/home');
          })
          .catch((err) => {
            const message = (err.response && err.response.data && err.response.data.message)
              || err.message || err.toString();
            this.$toasted.show(message).goAway(1500);
          });
      }
      this.clearInputs();
    },
    // oauthRedirect(){
    //   axios.get("localhost:8081/login/oauth/google")
    // },
    clearInputs() {
      this.user.username = '';
      this.user.password = '';
    },
  },
};
</script>
<style scoped>
@import "~bootstrap/dist/css/bootstrap.min.css";
.btn-google {
  color: #fff;
  background-color: #dd4b39;
  border-color: rgba(0, 0, 0, 0.2);
}
.btn-social {
  position: relative;
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
h3 {
  margin: 40px 0 0;
}
</style>
