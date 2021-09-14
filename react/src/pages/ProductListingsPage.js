import React, { useState } from 'react';
import Header from '../components/Header';
import ProductTable from '../components/ProductTable';
import ProductTableHeader from '../components/ProductTableHeader';

function ProductListingsPage() {
    const user = localStorage.getItem("user");
    const [cartAlert, setCartAlert] = useState(false);

    return (
        <div>
            <Header isLoggedIn={user !== null}/>
            <div className="container">
                <ProductTableHeader alert={cartAlert} setAlert={setCartAlert}/>
                <ProductTable setAlert={setCartAlert}/>
            </div>
        </div>
    );
}

export default ProductListingsPage;