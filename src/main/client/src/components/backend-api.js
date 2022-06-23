import axios from 'axios';

const AXIOS = axios.create({
  baseURL: 'http://localhost:8081/api/',
  timeout: 1000,
});
export default {
  backendConnection() {
    return AXIOS.get('test/');
  },
  login(user) {
    return AXIOS.post('/auth/signin', user);
  },
  getModel() {
    return AXIOS.get('http://localhost:8081/api/competence/getmodel');
  },
  getAllCompetences() {
    return {
      response: [
        {
          competenceName: 'Java',
          description: 'De leukste taal.',
          date: '2021-11-15',
          time: '18:00:01',
          endDate: '2021-11-15',
          endTime: '21:00:00',
        },
        {
          competenceName: 'C#',
          description: 'Een onbekende taal.',
          date: '2021-11-16',
          time: '18:00:02',
          endDate: '2021-11-16',
          endTime: '21:00:00',
        },
        {
          competenceName: '.Net',
          description: 'Een onbekende taal.',
          date: '2021-04-14',
          time: '18:00:00',
          endDate: '2021-04-14',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Business',
          description: 'Niet programmeren.',
          date: '2021-11-15',
          time: '18:00:04',
          endDate: '2021-11-15',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Mendix',
          description: 'Low code programmeren.',
          date: '2021-04-05',
          time: '18:00:05',
          endDate: '2021-04-05',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Pega',
          description: 'No code programmeren.',
          date: '2021-04-10',
          time: '18:00:06',
          endDate: '2021-04-10',
          endTime: '21:00:00',
        },
        {
          competenceName: '.Net',
          description: 'Testen!',
          date: '2021-04-10',
          time: '18:00:07',
          endDate: '2021-04-10',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Bar',
          description: 'Zuipen!4',
          date: '2021-04-14',
          time: '18:00:08',
          endDate: '2021-04-14',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Bar',
          description: 'Zuipen!3',
          date: '2021-04-13',
          time: '18:00:09',
          endDate: '2021-04-13',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Bar',
          description: 'Zuipen!2',
          date: '2021-04-12',
          time: '18:00:10',
          endDate: '2021-04-12',
          endTime: '21:00:00',
        },
        {
          competenceName: 'Bar',
          description: 'Zuipen!1',
          date: '2021-04-11',
          time: '18:00:11',
          endDate: '2021-04-11',
          endTime: '21:00:00',
        },
      ],
    };
  },
};
