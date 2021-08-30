import React from 'react';
import { useParams, useHistory } from 'react-router-dom';
import Header from '../components/Header';
import ProductForm from '../components/ProductForm';
import useFetch from 'react-fetch-hook';

function ProductFormPage() {
    const {pid} = useParams();
    const history = useHistory();
    const { isLoading, data, error } = useFetch(process.env.REACT_APP_BASE_API_URL+'/products/'+pid, {
        depends: [typeof pid !== 'undefined']
    });

    if (error) {
        history.push("/products");
    }

    return (
        <div>
            <Header/>
            <br/>
            <div className='container col-md-5'>
                <h2>{typeof pid == 'undefined' ? 'New Product' : 'Edit Product '+pid}</h2>
                {!isLoading && <ProductForm pc={data} />}
            </div>
        </div>
    );
}

export default ProductFormPage;