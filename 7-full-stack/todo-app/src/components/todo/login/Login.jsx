import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";
import './Login.css';

export default function Login(){

    const [loginCradentials, setLoginCradentials]= useState([]);
    const [isUnAuthorized, setIsUnAuthorized]= useState(false); 
    const navigate= useNavigate();
    const authContext= useAuth();


    const handleLoginFields= (ev)=>{
        setLoginCradentials((currentCrad)=> ({
            ...currentCrad,
            [ev.target.name]: ev.target.value
        }))
    }

    const handleLogin= async()=>{
        if(await authContext.login(loginCradentials["username"], loginCradentials["password"])){
            setIsUnAuthorized(false);
            navigate(`/welcome/${loginCradentials["username"]}`);
            return
        }
        setIsUnAuthorized(true);
    }

    return(
        <div className="login-section">
            <h1>Time To Login!</h1>
            {isUnAuthorized && <div className="unauthorized-message"> Authentication Failed, please check your cradentials</div>}
            <div className="username">
                <label htmlFor="username"> username:</label>
                <input type="text" name="username" id="username" onChange={(ev)=> handleLoginFields(ev)}/>
            </div>

            <div className="password">
                <label htmlFor="password"> password:</label>
                <input type="password" name="password" id="password" onChange={(ev)=> handleLoginFields(ev)}/>
            </div>

            <button type="button" className="login-btn" onClick={()=> handleLogin()}> Login </button>
        </div>
    );
}