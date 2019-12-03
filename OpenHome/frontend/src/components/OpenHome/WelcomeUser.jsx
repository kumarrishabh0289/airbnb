import React, { Component } from 'react'
import { Link } from 'react-router-dom'


class WelcomeUser extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            welcomeMessage: 'Hey You Are Authorized'
        }
        
        
    }

    render() {
        return (
            <>
                <h1>Welcome to User Dashboard</h1>
                <div className="container">
                    Welcome {this.props.match.params.name}.
                   
                </div>
                <div className="container">
                    
                       
                </div>
                <div className="container">
                    {this.state.welcomeMessage}
                    <Link className="nav-link" to="/">Search</Link>
                </div>

            </>
        )
    }



}


export default WelcomeUser