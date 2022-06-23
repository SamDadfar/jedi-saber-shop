/* eslint-disable class-methods-use-this */
import axios from 'axios';

class AuthService {
  AXIOS = axios.create({
    baseURL: 'http://localhost:8081/api/',
    timeout: 1000,
  });

  login(user) {
    return this.AXIOS
      .post('auth/signin', { username: user.username, password: user.password })
      .then((response) => {
        if (response.data) {
          localStorage.setItem('data', JSON.stringify(response.data));
        }

        return response;
      });
  }

  register(user) {
    return this.AXIOS.post('auth/signup',
      { username: user.username, email: user.email, password: user.password });
  }

  logout() {
    localStorage.removeItem('data');
  }
}
export default new AuthService();
