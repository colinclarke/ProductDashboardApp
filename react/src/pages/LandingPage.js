import React from 'react';


function LandingPage() {
  return (
    <div>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
      <title>Product Dashboard</title>
      <br />
      <div className="container col-md-5">
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">Product Dashboard</h5>
            <p className="card-text">
              This simple application allows users to perform Create,
              Read, Update, and Delete (CRUD) methods on a set of products.
              Only admins can update the price of items.
            </p>
            <div className="d-flex justify-content-around">
              <a href="/products" className="btn btn-primary">Go to dashboard</a>
              <a href="/login" className="btn btn-primary">Login</a>
              <a href="/logout" className="btn btn-primary">Logout</a>
              <a href="/createnewuser" className="btn btn-primary">Create new user</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LandingPage;