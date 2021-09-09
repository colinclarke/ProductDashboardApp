import React from 'react';
import Header from '../components/Header';
import ProductTable from '../components/ProductTable';
import ProductTableHeader from '../components/ProductTableHeader';

function ProductListingsPage() {
    return (
        <div>
            <Header/>
            <div className="container">
                <ProductTableHeader/>
                <ProductTable/>
            </div>
        </div>
    );
}

export default ProductListingsPage;