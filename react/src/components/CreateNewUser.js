import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import { useHistory } from 'react-router';
import FetchService from '../services/FetchService';

function CreateNewUser() {
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const allRoles = ["ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER"];
    const [roles, setRoles] = useState([]);
    const history = useHistory();
    const isAdmin = (localStorage.getItem("user") !== null) && JSON.parse(localStorage.getItem("user")).roles.includes("ROLE_ADMIN");

    let roleCheckboxes = [];
    for (let i in allRoles) {
        let role = allRoles[i];
        roleCheckboxes.push(
            <Form.Check type="checkbox" value={role} label={role} key={i} id={i}
                onChange={()=> {
                    if (roles.includes(role)) {
                        setRoles(roles.filter(e => e !== role))
                    } else {
                        roles.push(role);
                        setRoles(roles);
                    }
                }}/>
        );
    };
    
    function onSubmit(event) {
        let promise = FetchService.NewUser(username, password, roles);

        promise
            .then(response => {
                if (!response.ok) {
                    throw new Error('Response not ok!')
                }
                if (isAdmin) {
                    history.push("/");
                } else {
                    history.push("/login");
                }
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
        event.preventDefault();

    }

    return (
        <Form onSubmit={onSubmit}>
            <Form.Group className="mb-3" controlId="username">
                <Form.Label>Username</Form.Label>
                <Form.Control type = "text" value = {username} onChange={(event)=> setUsername(event.target.value)}></Form.Control>
            </Form.Group>
            <Form.Group className="mb-3" controlId="password">
                <Form.Label>Password</Form.Label>
                <Form.Control type = "password" value = {password} onChange={(event)=> setPassword(event.target.value)}></Form.Control>
            </Form.Group>
            { isAdmin &&
            <Form.Group className="mb-3" controlId="roles">
                <Form.Label>Roles</Form.Label>
                {roleCheckboxes}
            </Form.Group>
            }

            <div className="d-flex justify-content-around">
               <a href="/landingPage" className="btn btn-danger">Cancel</a> 
                <input type="submit" className="btn btn-success" value="Save"/>
            </div>
        </Form>

        
    )
}

export default CreateNewUser;