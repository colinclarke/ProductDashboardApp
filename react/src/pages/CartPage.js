import React from 'react';
import Stripe from "react-stripe-checkout";
import axios from "axios";
import useFetch from 'react-fetch-hook';
import {useHistory} from 'react-router-dom';
import CartContents from '../components/CartContents';

function CartPage() {

    const history = useHistory();
    const user = localStorage.getItem('user') === null ? null : JSON.parse(localStorage.getItem('user'));
    const { isLoading, data, error } = useFetch(process.env.REACT_APP_BASE_API_URL+'/cart/'+user.id, {
        headers: {Authorization: 'Bearer '+user.token},
        depends: [user !== null]
    });

    if (error) {
        history.push('/');
    }

    const totalPrice = data.reduce((prevItem, curItem) => (prevItem.product.price*prevItem.quantity) + (curItem.product.price*curItem.quantity));

    async function handleToken(token) {
        console.log(token);
        await axios.post(process.env.REACT_APP_BASE_API_URL+"/payment/charge", "", {headers: {
            Authorization: "Bearer "+JSON.parse(localStorage.getItem("user")).token,
            token: token.id,
            amount: {totalPrice},
        },})
        .then(() => {
           alert("Payment Success");
        })
        .catch((error) => {
           alert(error);
        });
    }

    return (
        <div className="App">
            {!isLoading && 
                <CartContents cart={data} totalPrice={totalPrice} />
            }
            <Stripe
            stripeKey="pk_test_51JPKxyG8YVxXlobJkSvbwGXNpZk1I00iw4PzpdUdC5sWTiqkfOr9pmHTkFrNvhURV90F9O6o7WipZWhAPVwz2Yva00iAQt1xZ4"
            token={handleToken}
            />
        </div>
    );
}

export default CartPage;