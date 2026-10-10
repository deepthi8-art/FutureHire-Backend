import axios from 'axios';

// API Base URL — uses Vercel env var in production, falls back to Render backend
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'https://futurehire-backend.onrender.com/api';


const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 60000, // 60s — Render free tier cold start can take ~30-50s
});

// Request Interceptor: Attach JWT Bearer Token if available
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('futurehire_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor: Global Error Handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      // Clear token on 401 unauthorized
      localStorage.removeItem('futurehire_token');
      localStorage.removeItem('futurehire_user');
    }
    return Promise.reject(error);
  }
);

export default api;
