import React, { Component } from 'react'



class SignUp extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            welcomeMessage: 'Sign Up Page'
        }
        
        
    }

    render() {
        return (
            <>
                <h1>Welcome!</h1>
                <div className="container">
                    Welcome {sessionStorage.googleEmail}   {sessionStorage.googleName}.
                   
                </div>
                <div className="container">
                    
                       
                </div>
                <div className="container">
                    {this.state.welcomeMessage}
                </div>

            </>
        )
    }



}


export default SignUp