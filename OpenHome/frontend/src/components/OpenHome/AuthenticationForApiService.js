import axios from 'axios'
import { API_URL } from '../../Constants'

export const AUTHENTICATED_USER_SESSION = 'authenticatedUser'

class AuthenticationForApiService {

    

    authenticate(email, password) {
        return axios.post(`${API_URL}/authenticate`, {
            email,
            password
        })
    }

    registerSuccessfulLogin(username, token) {
        sessionStorage.setItem(AUTHENTICATED_USER_SESSION, username)
        
        
    }

    
    logout() {
        sessionStorage.removeItem(AUTHENTICATED_USER_SESSION);
        sessionStorage.removeItem("googleEmail");
        sessionStorage.removeItem("googleName");
        sessionStorage.removeItem("userRole");
        sessionStorage.removeItem("userEmail");
        sessionStorage.removeItem("userName");
        sessionStorage.removeItem("userId");
        
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(AUTHENTICATED_USER_SESSION)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(AUTHENTICATED_USER_SESSION)
        if (user === null) return ''
        return user
    }

  
}

export default new AuthenticationForApiService()