import React from 'react';

function CartContents({cart, totalPrice}) {

    const cartItemList = [];
    for (let i in cart) {
        let cartItem = cart[i];
        cartItemList.push(
            <tr key={i}>
                <th scope="row">{cartItem.product.name}</th>
                <td>{cartItem.quantity}</td>
                <td>{cartItem.product.price}</td>
                <td>{(cartItem.quantity*cartItem.product.price).toFixed(2)}</td>
            </tr>
        )
    }

    return (
        <div className="my-3">
            <table className="table">
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
                </tbody>
            </table>
            <div className="d-flex justify-content-end">
                <h3 style={{color: 'green', fontWeight: 'bold'}}>{totalPrice}</h3>
            </div>
        </div>
    )
}

export default CartContents;