/* eslint-disable import/prefer-default-export */
import Vue from "vue";
import Vuex from "vuex";
import authService from "../services/auth.service";

Vue.use(Vuex);
const data = JSON.parse(localStorage.getItem("data"));
const initialState = data
  ? { status: { loggedIn: true }, data, msg: data.msg }
  : { status: { loggedIn: false }, data: { user: {} }, msg: "" };

export const auth = {
  state: initialState,
  mutations: {
    loginSuccess(state, res) {
      state.status.loggedIn = true;
      state.data = res;
      state.msg = "Successfully logged in";
    },
    loginFailure(state) {
      state.status.loggedIn = false;
      state.data = {};
      state.msg = "Failure login";
    },
    logout(state) {
      state.status.loggedIn = false;
      state.data = {};
      state.msg = "Successfully logged out";
    },
    registerSuccess(state) {
      state.status.loggedIn = false;
      state.msg = "Successfully new user registered ";
    },
    registerFailure(state) {
      state.status.loggedIn = false;
      state.msg = "Failure register";
    },
  },
  actions: {
    login({ commit }, userDto) {
      return new Promise((resolve, reject) => {
        authService
          .login(userDto)
          .then((response) => {
            if (response.status === 200) {
              console.log("Login successful");
              commit("loginSuccess", response.data);
            }
            resolve(response);
          })
          .catch((error) => {
            commit("loginFailure");
            reject(error);
          });
      });
    },
    register({ commit }, userDto) {
      return new Promise((resolve, reject) => {
        authService
          .register(userDto)
          .then((response) => {
            commit("registerSuccess");
            resolve(response.data);
          })
          .catch((error) => reject(error));
      });
    },
    logout({ commit }) {
      authService.logout();
      commit("logout");
    },
  },
};
