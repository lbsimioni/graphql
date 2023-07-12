import axios from 'axios';

const api = axios.create({
    baseURL: 'http://174.101.101.103:8080/graphql'
});

export default api;