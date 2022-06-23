import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8081/api/auth/test/';

class UserService {
  // eslint-disable-next-line class-methods-use-this
  getPublicContent() {
    return axios.get(`${API_URL}all`);
  }

  // eslint-disable-next-line class-methods-use-this
  getUserBoard() {
    return axios.get(`${API_URL}user`, {
      headers: authHeader(),
    });
  }

  // eslint-disable-next-line class-methods-use-this
  getManagerBoard() {
    return axios.get(`${API_URL}comp_manager`, {
      headers: authHeader(),
    });
  }

  // eslint-disable-next-line class-methods-use-this
  getAdminBoard() {
    return axios.get(`${API_URL}admin`, {
      headers: authHeader(),
    });
  }
}

export default new UserService();
