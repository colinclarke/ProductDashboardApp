import React from 'react';
import { Alert, Button } from 'react-bootstrap';

function ProductTableHeader({alert, setAlert}) {

    const isAdmin = (localStorage.getItem("user") !== null) && JSON.parse(localStorage.getItem("user")).roles.includes("ROLE_ADMIN");

    return (
        <div className="my-3">
            <Alert variant="success" show={alert} dismissible onClose={()=>setAlert(false)}>
                <div className="d-flex justify-content-center">Item added to cart</div>
            </Alert>
            <div className="my-3 d-flex justify-content-start">
                {isAdmin && 
                    <Button variant="success" href="/products/add">Add New Product</Button>
                }
            </div>
        </div>
    );
}

export default ProductTableHeader;