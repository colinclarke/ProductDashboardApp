import React from 'react';
import { useParams } from 'react-router-dom';
import Header from '../components/Header';
import useFetch from 'react-fetch-hook';
import ProductForm from '../components/ProductForm';

function ProductFormPage() {

    const {pid} = useParams();
    let productExists = true;
    let {isLoading, error, data} = useFetch(process.env.REACT_APP_BASE_API_URL+'products');
    if (typeof pid == 'undefined') {
        productExists = false;
    }

    console.log(isLoading, error && error.status, data);

    if (isLoading) return "Loading...";
    if (error) return "Error fetching data";

    return (
        <div>
            <Header/>
            <div className='container col-md-5'>
                <h2>{typeof pid == 'undefined' ? 'New Product' : 'Edit Product '+pid}</h2>
                <ProductForm productExists={productExists} product={data}/>
            </div>
        </div>
    );
}

export default ProductFormPage;