import React, { useState, useEffect } from 'react';
import FetchService from '../services/FetchService';
import ProductTableRow from './ProductTableRow';

function ProductTable() {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        getProducts();
    },[]);

    async function getProducts() {
        FetchService.GetProducts()
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setProducts(data));
    }

    return (
        <table className="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Subtotal</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {products.map((product, i) => <ProductTableRow key={i} product={product} update={getProducts}/>)}
            </tbody>
        </table>
    );
}

export default ProductTable;