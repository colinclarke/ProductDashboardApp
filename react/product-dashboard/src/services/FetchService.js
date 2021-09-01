const requestOptions = {
    mode: 'cors',
    headers: { 'Content-Type': 'application/json' },
};

const FetchService = {
    GetProducts: () => {
        requestOptions.method = 'GET';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products', requestOptions);
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
    },
    DeleteProduct: (id) => {
        requestOptions.method = 'DELETE';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products/delete/'+id, requestOptions);
    }
}

export default FetchService;