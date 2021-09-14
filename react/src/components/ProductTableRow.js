import React, { useState } from 'react';
import { Button } from 'react-bootstrap';
import { useHistory } from 'react-router';
import FetchService from '../services/FetchService';

function ProductTableRow({pc, update, setAlert}) {

    const [quantity, setQuantity] = useState(1);
    const history = useHistory();
    const user = localStorage.getItem("user");
    const isAdmin = user !== null && JSON.parse(user).roles.includes('ROLE_ADMIN');
    const isUser = user !== null && JSON.parse(user).roles.includes('ROLE_USER');
    const isManager = user !== null && JSON.parse(user).roles.includes('ROLE_MANAGER');

    function handleDelete() {
        FetchService.DeleteProduct(pc.product.id).then(() => update()).catch(error => console.error(error));
    }

    function handleAddToCart() {
        if (user === null) {
            history.push("/login");
        } else {
            FetchService.AddProductToCart(JSON.parse(user).id, pc.product.id, quantity)
                .then(async response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    setAlert(true);
                    await new Promise(r => setTimeout(r, 2000));
                    setAlert(false);
                })
                .catch(error => console.error(error));
            setQuantity(1);
        }
    }

    return (
        <tr>
            <td>{pc.product.id}</td>
            <td>{pc.product.name}</td>
            <td>{pc.category.name}</td>
            <td>{pc.product.quantity}</td>
            <td>${pc.product.price}</td>
            <td>${(pc.product.quantity*pc.product.price).toFixed(2)}</td>
            <td>
                <div className="d-flex justify-content-around">
                    { ((!isAdmin && !isManager) || isUser) && 
                        <form>
                            <input className="mx-3" type="number" min="1" max={pc.product.quantity} value={quantity} onChange={(e) => setQuantity(e.target.value)}/>
                            <Button variant="primary" onClick={handleAddToCart}>Add to cart</Button>
                        </form> 
                    }
                    { (isAdmin || isManager) && <Button variant="secondary" href={"/products/edit/"+pc.product.id}>Edit</Button> }
                    { isAdmin && <Button variant="danger" onClick={handleDelete}>Delete</Button> }
                </div>
            </td>
        </tr>
    );
}

export default ProductTableRow;