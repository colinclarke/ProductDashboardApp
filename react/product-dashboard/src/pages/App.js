import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import LandingPage from './LandingPage';
import ProductForm from './ProductForm';
import ProductListings from './ProductListings';

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/products/add">
                    <ProductForm/>
                </Route>
                <Route path="/products/edit/:pid">
                    <ProductForm/>
                </Route>
                <Route path="/products">
                    <ProductListings/>
                </Route>
                <Route path="/">
                    <LandingPage/>
                </Route>
            </Switch>
        </Router>
    )
}

export default App;