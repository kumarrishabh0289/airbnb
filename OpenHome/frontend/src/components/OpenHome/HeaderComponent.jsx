import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationForApiService from './AuthenticationForApiService.js'


class HeaderComponent extends Component {
    render() {
        const isUserLoggedIn = AuthenticationForApiService.isUserLoggedIn();
        

        return (
            <header>
                <nav className="navbar navbar-expand-md">
                    <div><img src="logo.png" height="40" width="55" alt="Logo"></img> <a href="/" className="navbar-brand">Open Home</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to="/welcome/OpenHome">Home</Link></li>}
                        
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationForApiService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default HeaderComponent