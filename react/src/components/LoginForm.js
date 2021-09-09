import React, {useState} from 'react'
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />



function LoginForm({Login,error}) {
    const [details, setDetails] = useState({username: "", password: ""});

    const submitHandler= e => {
        e.preventDefault();
        console.log(details);
        Login(details);
    }

    return (
        <form onSubmit={submitHandler}> 
        {(error !=="") ? (<div className="error">{error} </div> ) : ""}
        <div className="simple-login-container">
          <h2>Login Form</h2>
          <div className="row">
            <div className="col-md-12 form-group">
              <input type="text" className="form-control" placeholder="Username" onChange ={e => setDetails({...details, username: e.target.value})}value={details.username} />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <input type="password" placeholder="Enter your Password" className="form-control" onChange ={e => setDetails({...details, password: e.target.value})}value={details.passowrd} />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <input type="submit" className="btn btn-block btn-login"  />
            </div>
          </div>
        </div>
      
      </form>
    );
  }


export default LoginForm;