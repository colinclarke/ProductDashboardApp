import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';

function ProductForm({productExists = false, product = null}) {
    console.log(productExists, product);
    const [name, setName] = useState(productExists ? product.name : '');
    const [quantity, setQuantity] = useState(productExists ? product.quantity : undefined);
    const [price, setPrice] = useState(productExists ? product.price : undefined);

    function onSubmit(event) {
        console.log(name, quantity, price);
        console.log(JSON.stringify({ 
            id: product.id,
            name: name,
            quantity: quantity,
            price: price
         }));
        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ 
                id: product.id,
                name: name,
                quantity: quantity,
                price: price
             })
        };
        fetch(process.env.REACT_APP_BASE_API_URL+'products', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));
        event.preventDefault();
    }

    let productIdFormGroup = (
        <Form.Group className="mb-3" controlId="productId">
            <Form.Label>Product Id</Form.Label>
            <Form.Control type="text" value={product.id} readOnly/>
        </Form.Group>
    );

    return (
        <Form onSubmit={onSubmit}>

            {productExists && productIdFormGroup}

            <Form.Group className="mb-3" controlId="productName">
                <Form.Label>Product Name</Form.Label>
                <Form.Control type="text" value={name} onChange={(event) => setName(event.target.value)}/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="productQuantity">
                <Form.Label>Product Quantity</Form.Label>
                <Form.Control type="text" value={quantity} onChange={(event) => setQuantity(event.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="productPrice">
                <Form.Label>Product Price</Form.Label>
                <Form.Control type="text" value={price} onChange={(event) => setPrice(event.target.value)}/>
            </Form.Group>

            <div className="d-flex justify-content-around">
                <a href="/products" className="btn btn-danger">Cancel</a>
                <input type="submit" className="btn btn-success" value="Save"/>
            </div>
        </Form>
    );
}

export default ProductForm;