import React from 'react';
import { Button } from 'react-bootstrap';

function ProductTableHeader() {

    const isAdmin = (localStorage.getItem("user") !== null) && JSON.parse(localStorage.getItem("user")).roles.includes("ROLE_ADMIN");

    return (
        <div className="my-3 d-flex justify-content-start">
            {isAdmin && 
                <Button variant="success" href="/products/add">Add New Product</Button>
            }
        </div>
    );
}

export default ProductTableHeader;