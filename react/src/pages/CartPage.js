import React from 'react';
import Stripe from "react-stripe-checkout";
import axios from "axios";
import useFetch from 'react-fetch-hook';
import {useHistory} from 'react-router-dom';
import CartContents from '../components/CartContents';
import Header from '../components/Header';

function CartPage() {

    const history = useHistory();
    const user = localStorage.getItem('user') === null ? null : JSON.parse(localStorage.getItem('user'));
    const { isLoading, data, error } = useFetch(process.env.REACT_APP_BASE_API_URL+'/cart/'+user.id, {
        headers: {Authorization: 'Bearer '+user.token},
        depends: [user !== null]
    });

    console.log(data);

    if (error) {
        history.push('/');
    }

    let totalPrice = 0;
    if (!isLoading) {
        for (let i in data) {
            let cartItem = data[i];
            totalPrice += (cartItem.product.price*cartItem.quantity);
        }
    }

    async function handleToken(token) {
        console.log(token);
        await axios.post(process.env.REACT_APP_BASE_API_URL+"/payment/charge", "", {headers: {
            Authorization: "Bearer "+JSON.parse(localStorage.getItem("user")).token,
            token: token.id,
            amount: totalPrice,
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
            <Header/>
            <div className="container col-md-5">
                <h2 className="my-3">Checkout</h2>
                {!isLoading && 
                <div>
                    <CartContents cart={data} totalPrice={totalPrice} />
                    <div className="d-flex justify-content-end">
                        <Stripe
                        stripeKey="pk_test_51JPKxyG8YVxXlobJkSvbwGXNpZk1I00iw4PzpdUdC5sWTiqkfOr9pmHTkFrNvhURV90F9O6o7WipZWhAPVwz2Yva00iAQt1xZ4"
                        token={handleToken}
                        />
                    </div>
                </div>
                }
            </div>
        </div>
    );
}

export default CartPage;