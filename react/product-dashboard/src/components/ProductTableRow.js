import React, { useState } from 'react';
import { Button } from 'react-bootstrap';
import FetchService from '../services/FetchService';

function ProductTableRow({pc, update}) {

    const [quantity, setQuantity] = useState(1);

    function handleDelete() {
        FetchService.DeleteProduct(pc.product.id).then(() => update()).catch(error => console.error(error));
    }

    function handleAddToCart() {
        return;
    }

    return (
        <tr>
            <td>{pc.product.id}</td>
            <td>{pc.product.name}</td>
            <td>{pc.category.name}</td>
            <td>{pc.product.quantity}</td>
            <td>{pc.product.price}</td>
            <td>{pc.product.quantity*pc.product.price}</td>
            <td>
                <div className="d-flex justify-content-around">
                    <form>
                        <input type="number" min="1" max={pc.product.quantity} value={quantity} onChange={(e) => setQuantity(e.target.value)}/>
                        <Button variant="primary" onClick={handleAddToCart}>Add to cart</Button>
                    </form>
                    <Button variant="secondary" href={"/products/edit/"+pc.product.id}>Edit</Button>
                    <Button variant="danger" onClick={handleDelete}>Delete</Button>
                </div>
            </td>
        </tr>
    );
}

export default ProductTableRow;