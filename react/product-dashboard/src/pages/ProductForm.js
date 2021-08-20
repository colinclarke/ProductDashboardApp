import React from 'react';
import { useParams } from 'react-router-dom';

function ProductForm() {

    let {pid} = useParams();

    return (
        <p>{typeof pid == 'undefined' ? 'New Product' : 'Edit Product '+pid}</p>
    );
}

export default ProductForm;