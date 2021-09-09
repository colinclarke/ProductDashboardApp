import React from 'react';
import { Button } from 'react-bootstrap';

function ProductTableHeader() {
    return (
        <div className="my-3 d-flex justify-content-start">
            <Button variant="success" href="/products/add">Add New Product</Button>
        </div>
    );
}

export default ProductTableHeader;