import { createContext, useContext, useState } from "react";
import { executeJwtAuthLogin } from "../services/api/AuthenticationApiService";
import {addKeyToInterceptorConfigHeaders} from "../services/api/ApiClient";

// 1- create context
const AuthContext= createContext();
export const useAuth= ()=> useContext(AuthContext);

// 2- share the data between different components
export default function AuthProvider({children}){
    const [isLoggedIn, setIsLoggedIn]= useState(false);
    const [username, setUsername]= useState(null);

    // const [token, setToken]= useState(null);
    // const login= async (username, password)=>{
    //     const baToken= "Basic "+ window.btoa(`${username}:${password}`);
    //     try{
    //         const response= await executeBasicAuthLogin(baToken)
    //         if(response.status === 200){
    //             setIsLoggedIn(true);
    //             setUsername(username);
    //             setToken(baToken);
    //             // Set the Authorization header for all requests
    //             addKeyToInterceptorConfigHeaders("Authorization", baToken);
    //             return true;
    //         }
    //     }catch(err){
    //         logout();
    //         console.error("Login failed", err);
    //         return false;
    //     }
        
    //     logout();
    //     return false;
    // }
    

    const login= async (username, password)=>{
        try{
            const response= await executeJwtAuthLogin(username, password);
            if(response.status === 200){
                const jwtToken= `Bearer ${response.data.token}`;
                setIsLoggedIn(true);
                setUsername(username);
                // Set the Authorization header for all requests
                addKeyToInterceptorConfigHeaders("Authorization", jwtToken);
                return true;
            }
        }catch(err){
            logout();
            console.error("Login failed", err);
            return false;
        }
        
        logout();
        return false;
    }

    const logout= ()=>{
        setIsLoggedIn(false)
        setUsername(null);
    }
    // 3- put some states in the context
    return (
        <AuthContext.Provider value={{isLoggedIn, login, logout, username}}>
            {children}
        </AuthContext.Provider>
    );
}
