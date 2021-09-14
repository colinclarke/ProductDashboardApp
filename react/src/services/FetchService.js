const requestOptions = {
    mode: 'cors',
    headers: { 'Content-Type': 'application/json' },
};

function getJwtToken() {
    let user = localStorage.getItem("user");
    if (user !== null) {
        return 'Bearer '+JSON.parse(user).token;
    }
    return '';
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
    NewUser: (username, password, roles) => {
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
                username: username,
                password: password,
                roles: roles
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
        return fetch(process.env.REACT_APP_BASE_API_URL+'/cart/'+uid, requestOptions);
    },
    LoginRequest: (Username, password) => {
        requestOptions.method = 'POST';
        requestOptions.body = JSON.stringify({
            username: Username,
            password: password,
        });
        return fetch(process.env.REACT_APP_BASE_API_URL+'/auth/authenticate', requestOptions);
    },
    GetCart: (userid) => {
        requestOptions.headers.Authorization = getJwtToken();
        requestOptions.method = 'GET';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/cart/'+userid, requestOptions);
    },
    DeleteProductFromCart: (userid, productid) => {
        requestOptions.headers.Authorization = getJwtToken();
        requestOptions.method = 'DELETE';
        delete requestOptions.body;
        return fetch(process.env.REACT_APP_BASE_API_URL+'/cart/'+userid+'/product/'+productid, requestOptions);
    }
}

export default FetchService;