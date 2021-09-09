import React, {useState} from 'react';
import LoginForm from '../components/LoginForm';
import './Login.css';





function Login() {
    const adminUser = {
        Username: "admin",
        password: "admin123"
    }

    const [user, setUser] = useState({Username: ""});
    const [error, setError] = useState("");

    const Login = details => {
        console.log(details);
       
        if (details.Username === adminUser.Username && details.password === adminUser.password){
            console.log("Logged in");
            setUser({
                Username: details.Username,
                
            } );
        }  else {
            console.log("Details do not match!");
            setError("Details do not match!");
    }
}

    

    const Logout = () => {
        setUser({Username: ""});
    }

    return (
        <div className= "Login">
           {(user.Username !== "") ? (
            <div className= "welcome">
                <h2> Welcome, <span> {user.Username}</span></h2>
                <button onClick={Logout}> Logout </button>
                </div>
           ) : (
               <LoginForm  Login={Login} error = {error}/>
           )}
        </div>
    );
}

export default Login;