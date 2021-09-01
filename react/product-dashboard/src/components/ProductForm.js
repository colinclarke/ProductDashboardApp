import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import { Button } from 'react-bootstrap';
import FetchService from '../services/FetchService';
import { useHistory } from 'react-router-dom';

function ProductForm({pc}) {
    const productExists = typeof pc !== 'undefined';
    const [name, setName] = useState(productExists ? pc.product.name : '');
    const [quantity, setQuantity] = useState(productExists ? pc.product.quantity : '');
    const [price, setPrice] = useState(productExists ? pc.product.price : '');
    const [category, setCategory] = useState(productExists ? pc.category.name : '');
    const history = useHistory();

    function onSubmit(event) {
        let promise = productExists ? 
            FetchService.EditProduct(pc.product.id, name, quantity, price, category) :
            FetchService.NewProduct(name, quantity, price, category);
        promise
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(() => history.push("/products"))
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
        event.preventDefault();
    }

    let productIdFormGroup = (
        <Form.Group className="mb-3" controlId="productId">
            <Form.Label>Product Id</Form.Label>
            <Form.Control type="text" value={productExists && pc.product.id} readOnly/>
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

            <Form.Group className="mb-3" controlId="productCategory">
                <Form.Label>Product Category</Form.Label>
                <Form.Control type="text" value={category} onChange={(event) => setCategory(event.target.value)}/>
            </Form.Group>

            <div className="d-flex justify-content-around">
                <Button variant="danger" type="button" href="/products">Cancel</Button>
                <Button variant="success" type="submit">Save</Button>
            </div>
        </Form>
    );
}

export default ProductForm;