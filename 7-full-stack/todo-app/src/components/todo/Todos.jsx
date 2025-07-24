import { useEffect, useState } from "react";
import {deleteTodoById, retrieveTodosByUserName } from "./services/api/TodoApiService";
import { useAuth } from "./context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ListTodos(){
    const [todos, setTodos]= useState([]);
    const [message, setMessage]= useState("");
    const authContext= useAuth();
    const navigate= useNavigate();

    const fetchUserTodos= ()=>{
        retrieveTodosByUserName(authContext.username)
        .then(res=> setTodos(res.data))
        .catch(err => {console.log(err)})
        .finally(()=> {console.log("clean up")})
    }

    const handleDeleteTodo= (id)=>{
        deleteTodoById(authContext.username, id)
        .then(_ => {
            setMessage(`Delete of todo with id= ${id} successful`)
            fetchUserTodos();
        })
        .catch(err => console.log(err))
    }

    const handleUpdateTodo= (id)=>{
       navigate(`/todos/${id}`);
    }

    const handleCreateTodo= ()=>{
       navigate(`/todos/create`);
    }

    

    useEffect(()=> fetchUserTodos(), [])

    return (
        <div className="container">
            <h1> Things You Want To Do!</h1>
            <br/>
            {message && <h6 className="bg-warning text-light"> {message} </h6>}
            <table className="table">
                <thead>
                    <tr>
                        <th> id</th>
                        <th> description</th>
                        <th> isDone?</th>
                        <th> targetDate</th>
                        <th> </th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        todos.map(todo => (
                            <tr key={todo.id}>
                                <td> {todo.id} </td>
                                <td> {todo.description} </td>
                                <td> {todo.done.toString()} </td>
                                <td> {todo.targetDate.toString()} </td>
                                <td>
                                    <button type="button" className="btn btn-danger" onClick={()=> handleDeleteTodo(todo.id)}>DELETE</button>
                                </td>
                                <td>
                                    <button type="button" className="btn btn-warning" onClick={()=> handleUpdateTodo(todo.id)}>UPDATE</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
            <button type="button" className="btn btn-success" onClick={()=> handleCreateTodo()}>CREATE</button>
        </div>
    );
}