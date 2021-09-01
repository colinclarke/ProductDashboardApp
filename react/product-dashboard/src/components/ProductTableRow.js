import React, { useState } from 'react';
import { Button } from 'react-bootstrap';
import FetchService from '../services/FetchService';

function ProductTableRow({product, update}) {

    const [quantity, setQuantity] = useState(1);

    function handleDelete() {
        FetchService.DeleteProduct(product.id).then(() => update()).catch(error => console.error(error));
    }

    function handleAddToCart() {
        return;
    }

    return (
        <tr>
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>category...</td>
            <td>{product.quantity}</td>
            <td>{product.price}</td>
            <td>{product.quantity*product.price}</td>
            <td>
                <div className="d-flex justify-content-around">
                    <form>
                        <input type="number" min="1" max={product.quantity} value={quantity} onChange={(e) => setQuantity(e.target.value)}/>
                        <Button variant="primary" onClick={handleAddToCart}>Add to cart</Button>
                    </form>
                    <Button variant="secondary" href={"/products/edit/"+product.id}>Edit</Button>
                    <Button variant="danger" onClick={handleDelete}>Delete</Button>
                </div>
            </td>
        </tr>
    );
}

export default ProductTableRow;