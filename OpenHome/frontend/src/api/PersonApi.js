import axios from 'axios'
import { API_URL } from '../../Constants'

class PersonApi {

    retrieveAllPerson(name) {
        //console.log('executed service')
        return axios.get(`${API_URL}/users/${name}`);
    }


}

export default new PersonApi()