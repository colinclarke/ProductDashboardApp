import React, {useState, useEffect, useCallback} from 'react';
import Stripe from "react-stripe-checkout";
import axios from "axios";
import {useHistory} from 'react-router-dom';
import CartContents from '../components/CartContents';
import Header from '../components/Header';
import FetchService from '../services/FetchService';

function CartPage() {

    const history = useHistory();
    const user = localStorage.getItem('user') === null ? null : JSON.parse(localStorage.getItem('user'));
    const [cart, setCart] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);

    const getCart = useCallback(async () => {
        FetchService.GetCart(user.id)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setCart(data);
                setTotalPrice(0);
                for (let i in data) {
                    let cartItem = data[i];
                    setTotalPrice(t => t + (cartItem.product.price*cartItem.quantity));
                }
            })
            .catch(error => console.error(error));
    }, [user.id]);

    useEffect(() => {
        getCart();
    },[getCart]);

    async function handleToken(token) {
        console.log(token);
        await axios.post(process.env.REACT_APP_BASE_API_URL+"/payment/charge", "", {headers: {
            Authorization: "Bearer "+JSON.parse(localStorage.getItem("user")).token,
            token: token.id,
            amount: totalPrice,
            userid: user.id
        },})
        .then(() => {
            alert("Payment Success");
            history.push("/products");
        })
        .catch((error) => {
           alert(error);
        });
    }

    return (
        <div>
            <Header isLoggedIn={user !== null}/>
            <div className="container col-md-5">
                <h2 className="my-3">Checkout</h2>
                <div>
                    <CartContents cart={cart} totalPrice={totalPrice} getCart={getCart} />
                    <div className="d-flex justify-content-end">
                        <Stripe
                        stripeKey="pk_test_51JPKxyG8YVxXlobJkSvbwGXNpZk1I00iw4PzpdUdC5sWTiqkfOr9pmHTkFrNvhURV90F9O6o7WipZWhAPVwz2Yva00iAQt1xZ4"
                        token={handleToken}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CartPage;