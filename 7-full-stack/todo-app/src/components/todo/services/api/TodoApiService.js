import {ApiClient} from "./ApiClient";

export const retrieveTodosByUserName = (username) => 
    ApiClient.get(`user/${username}/todos`);

export const retrieveTodoById = (username, id) => 
    ApiClient.get(`user/${username}/todos/${id}`);

export const deleteTodoById= (username, id)=>
    ApiClient.delete(`/user/${username}/todos/${id}`);

export const updateTodoById= (username, id, todo)=> 
    ApiClient.put(`/user/${username}/todos/${id}`, todo);

export const createTodo = (username, todo) => 
    ApiClient.post(`/user/${username}/todos`, todo);

export const executeBasicAuthLogin = (token) => {
    return ApiClient.get('user/basic-auth',{
        headers: {
            Authorization: token
        }
    }); // test endpoint to verify login
}
