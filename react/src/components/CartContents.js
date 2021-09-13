import React from 'react';

function CartContents({cart, totalPrice}) {

    cartItemList = [];
    for (let i in cart) {
        let cartItem = cart[i];
        cartItemList.push(
            <tr>
                <th scope="row">{cartItem.product.name}</th>
                <td>{cartItem.quantity}</td>
                <td>{cartItem.product.price}</td>
                <td>{(cartItem.quantity*cartItem.product.price).toFixed(2)}</td>
            </tr>
        )
    }

    return (
        <table class="table">
            <thead>
                <tr>
                <th scope="col">Name</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Subtotal</th>
                </tr>
            </thead>
            <tbody>
                {cartItemList}
                <tr>
                    {totalPrice}
                </tr>
            </tbody>
        </table>
    )
}

export default CartContents;