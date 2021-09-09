import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import FetchService from '../services/FetchService';

function CreateNewUser() {
    
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    
    function onSubmit(event) {
        let promise = FetchService.NewUser(username, password);

        promise
            .then(response => {
                if (!response.ok) {
                    throw new Error('Response not ok!')
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

            <div className="d-flex justify-content-around">
               <a href="/landingPage" className="btn btn-danger">Cancel</a> 
                <input type="submit" className="btn btn-success" value="Save"/>
            </div>
        </Form>

        
    )
}

export default CreateNewUser;