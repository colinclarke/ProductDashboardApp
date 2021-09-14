import React from 'react';
import cart from '../assets/cart.svg';

function Header({isLoggedIn}) {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark" style={{backgroundColor: 'tomato'}}>
                <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul className="navbar-nav me-auto">
                        <li><a href="/products" className="nav-link mx-3">Products</a></li>
                    </ul>
                </div>
                <div className="mx-auto order-0">
                    <a className="navbar-brand mx-auto" href="/">Product CRUD App</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                </div>
                <div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
                    <ul className="navbar-nav ms-auto">
                        { isLoggedIn ? 
                        <li className="nav-item me-3">
                            <a className="nav-link" href="/cart"><img src={cart} alt="cart logo" className="mx-3"/>Cart</a>
                        </li>
                        :
                        <li className="nav-item me-3">
                            <a href="/login" className="btn btn-secondary">Login</a>
                        </li>
                        }
                    </ul>
                </div>
            </nav>
	    </header>
    );
}

export default Header;