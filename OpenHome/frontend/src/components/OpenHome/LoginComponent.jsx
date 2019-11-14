import React, { Component } from 'react'
import AuthenticationForApiService from './AuthenticationForApiService.js'
import GoogleLogin from 'react-google-login';

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: 'Rishabh',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
        this.responseGoogle = this.responseGoogle.bind(this)
    }

    handleChange(event) {

        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }



    loginClicked() {

        AuthenticationForApiService
            .authenticate(this.state.username, this.state.password)
            .then((response) => {
                AuthenticationForApiService.registerSuccessfulLogin(this.state.username, response.data.token)
                this.props.history.push(`/welcome/${this.state.username}`)
            }).catch(() => {
                this.setState({ showSuccessMessage: false })
                this.setState({ hasLoginFailed: true })
            })

    }

    responseGoogle = (response) => {
        console.log(response);
    }

    render() {
        return (
            <div>
                <h1>Login</h1>
                <div className="container">

                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                    {this.state.showSuccessMessage && <div>Login Sucessful</div>}

                    User Name: <input type="text" name="username" value={this.state.username} onChange={this.handleChange} />
                    Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                </div>
                <GoogleLogin
                    clientId="624602059574-qsv45kcgn89v376114ql2ps2t5rljfd7.apps.googleusercontent.com"
                    buttonText="Login"
                    onSuccess={this.responseGoogle}
                    onFailure={this.responseGoogle}
                    cookiePolicy={'single_host_origin'}
                />
                
            </div>
        )
    }
}

export default LoginComponent