import { ApiClient } from "./ApiClient";

export const executeJwtAuthLogin = (username, password) => 
    ApiClient.post('/authenticate', {username, password});