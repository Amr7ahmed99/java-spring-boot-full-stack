import axios from "axios";

export const ApiClient = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true, // only if you need cookies/auth headers
    headers: {
        "Content-Type": "application/json"
    }
});


export const addKeyToInterceptorConfigHeaders = (key, value) => {
    if (key && value) {
        ApiClient.interceptors.request.use(config => {
            config.headers[key] = value;
            return config;
        });
    }
}