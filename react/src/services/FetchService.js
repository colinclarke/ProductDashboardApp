const requestOptions = {
    mode: 'cors',
    headers: { 'Content-Type': 'application/json' },
};

function getJwtToken() {
    return 'Bearer '+localStorage.getItem('token');
}

const FetchService = {
    GetProducts: () => {
        delete requestOptions.headers.Authorization;
        requestOptions.method = 'GET';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products', requestOptions);
    },
    NewProduct: (name, quantity, price, category) => {
        requestOptions.headers.Authorization = getJwtToken();
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
        requestOptions.headers.Authorization = getJwtToken();
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
        requestOptions.headers.Authorization = getJwtToken();
        requestOptions.method = 'DELETE';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/products/delete/'+id, requestOptions);
    },
    NewUser: (username, password) => {
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
                username: username,
                password: password,
                role: 'USER_ROLE',
                enabled: true
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/create-new-user', requestOptions);
    },
    AddProductToCart: (uid, pid, quantity) => {
        requestOptions.headers.Authorization = getJwtToken();
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
            productId: pid,
            quantity: quantity
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/'+uid, requestOptions);
    },
    LoginRequest: (Username, password) => {
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
            Username: Username,
            password: password,
            
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/api/auth/authenticate', requestOptions);
    }

}

export default FetchService;