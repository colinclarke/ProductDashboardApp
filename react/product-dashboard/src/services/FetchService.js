const requestOptions = {
    mode: 'cors',
    headers: { 'Content-Type': 'application/json' },
};

const FetchService = {
    GetProduct: (id) => {
        requestOptions.method = 'GET';
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products/'+id, requestOptions);
    },
    NewProduct: (name, quantity, price, category) => {
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
            product: {
                name: name,
                quantity: quantity,
                price: price
            },
            category: {
                name: category
            }
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products/new', requestOptions);
    },
    EditProduct: (id, name, quantity, price, category) => {
        requestOptions.method = 'PUT';
        requestOptions.body = JSON.stringify({
            product: {
                id: id,
                name: name,
                quantity: quantity,
                price: price
            },
            category: {
                name: category
            }
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products/edit/'+id, requestOptions);
    }
}

export default FetchService;