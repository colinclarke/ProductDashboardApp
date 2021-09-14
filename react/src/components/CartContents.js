import React from 'react';
import FetchService from '../services/FetchService';
import { Button } from 'react-bootstrap';

function CartContents({cart, totalPrice, getCart}) {

    function handleDelete(productid) {
        let user = localStorage.getItem("user");
        if (user === null) return;
        FetchService.DeleteProductFromCart(JSON.parse(user).id, productid)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                getCart();
            })
            .catch(error => console.error(error));
    }

    const cartItemList = [];
    for (let i = 0; i < cart.length; i++) {
        let cartItem = cart[i];
        cartItemList.push(
            <tr key={i}>
                <td>{i+1}</td>
                <th scope="row">{cartItem.product.name}</th>
                <td>{cartItem.quantity}</td>
                <td>${cartItem.product.price}</td>
                <td>${(cartItem.quantity*cartItem.product.price).toFixed(2)}</td>
                <td>
                    <Button variant="danger" onClick={() => handleDelete(cartItem.product.id)}>Delete</Button>
                </td>
            </tr>
        )
    }

    return (
        <div className="my-3">
            <table className="table">
                <thead>
                    <tr>
                    <th scope="col"></th>
                    <th scope="col">Name</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Subtotal</th>
                    <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {cartItemList}
                </tbody>
            </table>
            <div className="d-flex justify-content-end">
                <h3 style={{color: 'green', fontWeight: 'bold'}}>${(Math.round(totalPrice * 100) / 100).toFixed(2)}</h3>
            </div>
        </div>
    )
}

export default CartContents;