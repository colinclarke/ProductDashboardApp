import React, {useState} from 'react'


function LoginForm({Login,error}) {
    const [details, setDetails] = useState({Username: "", password: ""});

    const submitHandler= e => {
        e.preventDefault();
        Login(details);

    }
    return (
        <form onSubmit={submitHandler}>
            <div className="form-inner">
                <h2> Login</h2>
                {(error !=="") ? (<div className="error">{error} </div> ) : ""}

                <div className="form-group">
                    <label htmlFor="Username"> Username:</label>
                    <input type="text" Username="Username" id="Username" onChange ={e => setDetails({...details, Username: e.target.value})}value={details.Username} />
                </div>
                
                <div className="form-group">
                    <label htmlFOr="password"> password: </label>
                    <input type="password" name="password" id="password"onChange ={e => setDetails({...details, password: e.target.value})}value={details.passowrd}/>
            </div>
            <input type="submit" value="LOGIN"/>
            </div>
        </form>
    )
}

export default LoginForm;