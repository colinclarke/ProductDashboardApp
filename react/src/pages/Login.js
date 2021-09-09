import React, {useState} from 'react';
import { useHistory } from 'react-router';
import LoginForm from '../components/LoginForm';
import FetchService from '../services/FetchService';
import './Login.css';

function Login() {

    const [user, setUser] = useState(localStorage.getItem("user"));
    const [error, setError] = useState("");
    const history = useHistory();

    const Login = details => {
        FetchService.LoginRequest(details.username, details.password)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                localStorage.setItem("user", JSON.stringify(data));
                setUser(data);
                history.push("/products");
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
                setError("Incorrect username or password");
            });
    }

    

    const Logout = () => {
        localStorage.removeItem("user");
        setUser(null);
    }

    return (
        <div className= "Login">
           {(user !== null) ? (
            <div className= "welcome">
                <h2> Welcome, <span> {user.username}</span></h2>
                <button onClick={Logout}> Logout </button>
                </div>
           ) : (
               <LoginForm  Login={Login} error = {error}/>
           )}
        </div>
    );
}

export default Login;