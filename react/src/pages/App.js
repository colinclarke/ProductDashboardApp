import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import LandingPage from './LandingPage';
import ProductFormPage from './ProductFormPage';
import ProductListingsPage from './ProductListingsPage';
import Login from './Login';
import CreateNewUserPage from './CreateNewUserPage'
import CartPage from './CartPage';


function App() {

    return (
        <Router>
            <Switch>
                <Route path="/products/add">
                    <ProductFormPage/>
                </Route>
                <Route path="/products/edit/:pid">
                    <ProductFormPage/>
                </Route>
                <Route path="/products">
                    <ProductListingsPage/>
                </Route>
                <Route path="/Login">
                    <Login/>
                </Route>
                <Route path = "/createNewUser">
                    <CreateNewUserPage/>
                </Route>
                <Route path="/cart">
                    <CartPage/>
                </Route>
                <Route path="/">
                    <LandingPage/>
                </Route>
            </Switch>
        </Router>
    )
}

export default App;