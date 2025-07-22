import { createContext, useContext, useState } from "react";

// 1- create context
const AuthContext= createContext();
export const useAuth= ()=> useContext(AuthContext);

// 2- share the data between different components
export default function AuthProvider({children}){
    const [isLoggedIn, setIsLoggedIn]= useState(false);
    const login= (username, password)=>{
        if(username === "Amr" && password === "123456"){
            setIsLoggedIn(true);
            return true
        }
        setIsLoggedIn(false);
        return false
    }

    const logout= ()=>{
        setIsLoggedIn(false)
    }
    // 3- put some states in the context
    return (
        <AuthContext.Provider value={{isLoggedIn, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
}
