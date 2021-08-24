import React from 'react';

function Header() {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark" style={{backgroundColor: 'tomato'}}>
                <div>
                    <a href="/" className="navbar-brand"> Product CRUD App </a>
                </div>

                <ul className="navbar-nav">
                    <li><a href="/products" className="nav-link">Products</a></li>
                </ul>
            </nav>
	    </header>
    );
}

export default Header;