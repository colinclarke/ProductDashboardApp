import React from 'react';
import { useParams } from 'react-router-dom';
import Header from '../components/Header';

function ProductForm() {

    let {pid} = useParams();

    return (
        <div>
            <Header/>
            <p>{typeof pid == 'undefined' ? 'New Product' : 'Edit Product '+pid}</p>
        </div>
    );
}

export default ProductForm;