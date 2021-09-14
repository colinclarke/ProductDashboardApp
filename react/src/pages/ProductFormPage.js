import React from 'react';
import { useParams, useHistory } from 'react-router-dom';
import Header from '../components/Header';
import ProductForm from '../components/ProductForm';
import useFetch from 'react-fetch-hook';

function ProductFormPage() {
    const {pid} = useParams();
    const history = useHistory();
    let user = localStorage.getItem("user");
    const { isLoading, data, error } = useFetch(process.env.REACT_APP_BASE_API_URL+'/products/'+pid, {
        headers: {Authorization: user !== null ? 'Bearer '+JSON.parse(user).token : ''},
        depends: [typeof pid !== 'undefined']
    });

    if (error) {
        history.push("/products");
    }

    return (
        <div>
            <Header isLoggedIn={user !== null}/>
            <br/>
            <div className='container col-md-5'>
                <h2>{typeof pid == 'undefined' ? 'New Product' : 'Edit Product '+pid}</h2>
                {!isLoading && <ProductForm pc={data} />}
            </div>
        </div>
    );
}

export default ProductFormPage;