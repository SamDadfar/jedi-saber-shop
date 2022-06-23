<template>
  <div class="container">
    <div class="card bg-light">
      <article class="card-body mx-auto" style="max-width: 400px">
        <h2 class="card-title mt-3 text-center">Sign Up</h2>
        <form ref="registerForm" v-on:submit.prevent="handleSignup">
          <div class="form-group input-group">
            <div class="input-group-prepend">
              <span class="input-group-text"> <i class="fa fa-user"></i> </span>
            </div>
            <input
            id="username"
            v-model="user.username"
            v-validate="'required'"
            class="form-control"
            placeholder="Username"
            type="text"
            :class="{ 'is-invalid': submitted && errors.has('userame') }"
            />
            <div v-if="submitted && errors.has('username')" class="invalid-feedback">
                  {{ errors.first("userame") }}
                </div>
          </div>
          <!-- form-group// -->
          <div class="form-group input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">
                <i class="fa fa-envelope"></i>
              </span>
            </div>
            <input
              id="email"
              class="form-control"
              placeholder="Email address"
              type="email"
              v-model="user.email"
              v-validate="'required|email'"
              :class="{ 'is-invalid': submitted && errors.has('email') }"
            />
            <div v-if="submitted && errors.has('email')" class="invalid-feedback">
                  {{ errors.first("email") }}
            </div>
          </div>
          <!-- form-group// -->
          <div class="form-group input-group">
            <div class="input-group-prepend">
              <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input
              id="password"
              class="form-control"
              placeholder="password"
              type="password"
              v-model="user.password"
              v-validate="{required: true, min: 6}"
              :class="{ 'is-invalid': submitted && errors.has('password') }"
            />
          </div>
          <!-- form-group// -->
          <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">
              Create Account
            </button>
          </div>
          <!-- form-group// -->
          <p class="text-center">
            Have an account? <a href="/login">Log In</a>
          </p>
        </form>
      </article>
    </div>
    <!-- card.// -->
  </div>
</template>

<script>
import User from '../../model/user';

export default {
  name: 'Signup',
  data() {
    return {
      user: new User('', '', ''),
      submitted: false,
      message: '',
    };
  },
  computed: {
    isloggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.isloggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleSignup() {
      this.submitted = true;
      // this.$validator.validate().then((valid) => {
      //   if (valid) {
      if (this.user.username && this.user.password && this.user.email) {
        this.$store.dispatch('register', this.user)
          .then((data) => {
            this.message = data.message;
            this.$toasted.show(this.message).goAway(1500);
            this.$router.push('/home');
          })
          .catch((err) => {
            this.message = (err.response || err.data || err.response.data.message)
              ? err.response.data.message : '';
            this.$toasted.show(this.message).goAway(1500);
          });
      }
    },
    clearAll() {
      this.user.username = '';
      this.user.email = '';
      this.user.password = '';
    },
  },
};
</script>
<style scoped>
h3 {
  margin: 40px 0 0;
}
</style>
