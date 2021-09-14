import React, { useState, useEffect } from 'react';
import FetchService from '../services/FetchService';
import ProductTableRow from './ProductTableRow';

function ProductTable(props) {

    const [pc, setPc] = useState([]);

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
            .then(data => setPc(data))
            .catch(error => console.error(error));
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
                {pc.map((p, i) => <ProductTableRow 
                    key={i} 
                    pc={p} 
                    update={getProducts} 
                    setAlert={props.setAlert}/>
                )}
            </tbody>
        </table>
    );
}

export default ProductTable;