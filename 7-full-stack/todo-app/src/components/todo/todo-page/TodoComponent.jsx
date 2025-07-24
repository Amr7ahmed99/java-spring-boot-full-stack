import {useNavigate, useParams} from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { createTodo, retrieveTodoById, updateTodoById } from "../services/api/TodoApiService";
import { useEffect, useState } from "react";
import { Field, Formik, Form, ErrorMessage } from "formik";
import "./TodoComponent.css";


export default function TodoComponent(){

    const {id}= useParams();
    const authContext= useAuth();
    const [description, setDescription]= useState("");
    const [done, setDone]= useState(false);
    const [targetDate, setTargetDate]= useState("");
    const username = authContext.username;
    const navigate = useNavigate();

    const fetchUserTodoById= ()=>{
         retrieveTodoById(username, id)
        .then(res =>{
            setDescription(res.data.description);
            setDone(res.data.done);
            setTargetDate(res.data.targetDate);
        })
        .catch(err => console.log(err))
    }

    const handleTodoCreationAndUpdate = (values) => {
        const todo={
            description: values.description,
            done: values.done,
            targetDate: new Date(values.targetDate)
        };

        if(!id) {
            // Create new todo
            createTodo(username, todo)
                .then(_ => navigate(`/todos`))
                .catch(err => console.log(err));
            return;
        }

        updateTodoById(username, id, todo)
        .then(_ => navigate(`/todos`))
        .catch(err => console.log(err));
    }

    const validate = (values) => {
        const errors = {};
        if (values.description.trim().length < 5) {
            errors.description = "Description must be at least 5 characters";
        }

        if (!values.targetDate) {
            errors.targetDate = "Target date is required";
        }
        return errors;
    }

    useEffect(()=> {
        if(id) {
            fetchUserTodoById();
        }
    }, [id])
    return (
        <div className="container">
            <h1>Todo</h1>
            <div>
                <Formik 
                    className="form"
                    initialValues={{description, targetDate, done}} 
                    enableReinitialize={true} 
                    onSubmit={handleTodoCreationAndUpdate}
                    validate={validate}
                    validateOnBlur= {false}
                    validateOnChange={false}>
                    {(props) => (
                        <Form>
                            <ErrorMessage name="description" component="div" className="alert alert-warning"/>
                            <ErrorMessage name="targetDate" component="div" className="alert alert-warning"/>
                            <fieldset className="form-group">
                                <label htmlFor="description">Description:</label>
                                <Field type="text" name="description" id="description" className="form-control" placeholder= "What you will do"/>
                            </fieldset>
                            <br/>
                            <fieldset className="form-group">
                                <label htmlFor="done" className="px-2">Is Done:</label>
                                <Field type="checkbox" name="done" className="form-check-input"/>
                            </fieldset>
                            <br/>
                            <fieldset className="form-group">
                                <label htmlFor="targetDate">Target Date:</label>
                                <Field type="date" name="targetDate" className="form-control"/>
                            </fieldset>
                            <br/>
                            <button type="submit" className="btn btn-success my-2">Save</button>
                        </Form>
                    )}
                </Formik>
            </div>
        </div>
    );
}