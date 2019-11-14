import React, { Component } from 'react'
import { Route, Redirect } from 'react-router-dom'
import AuthenticationForApiService from './AuthenticationForApiService.js'

class AuthenticatedRoute extends Component {
    render() {
        if (AuthenticationForApiService.isUserLoggedIn()) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }

    }
}

export default AuthenticatedRoute