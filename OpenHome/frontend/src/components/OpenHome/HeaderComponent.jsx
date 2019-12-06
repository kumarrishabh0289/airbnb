import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationForApiService from './AuthenticationForApiService.js'
import { API_URL } from '../../Constants'
import axios from 'axios';

class HeaderComponent extends Component {
    
    constructor(props) {
        super(props)
        
        this.state = {
            date: '',
            hours:0,
            mins:0

        }
       
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    async componentDidMount() {
        try {
          setInterval(async () => {
            axios
            .get(API_URL + "/admin/time", {
                headers: { "Content-Type": "application/json" }
            }).then(response => {
                
                if (response.status === 200) {
                    this.setState({

                       date: response.data
                    })

                }
                else {
     
                }
            })
            .catch(err => {
                console.log(err);

            });
          }, 3000);
        } catch(e) {
          console.log(e);
        }
  }

  changeTime = (e) => {
   
    console.log("changeTime login called")
  //  var headers = new Headers();
    //prevent page from refresh
    e.preventDefault();
 
    axios.post(API_URL + `/admin/time/addoffset/${this.state.hours}/${this.state.mins}`)
        .then((response) => {
            console.log("Status Code : ", response.status);
            console.log("response : ", response);
        });
}


    render() {
        const isUserLoggedIn = AuthenticationForApiService.isUserLoggedIn();
        const isUserVerified = AuthenticationForApiService.isUserVerified();
        console.log("isUserVerified",isUserVerified)

        return (
            <header>
                {/* <nav className="navbar navbar-expand-md"> */}
                <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                    <div><img src="logo.png" height="40" width="55" alt="Logo"></img> <a href="/" className="navbar-brand">Open Home</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li><Link className="nav-link" to="/welcome">Home</Link></li>}
                       
                        <li><Link className="nav-link" to=""><font color="red">Current Environment: {this.state.date}</font></Link></li>
                        <li>
                            <form onSubmit={this.changeTime}>
                            <h7 style={{ backgroundColor: "powderblue" }}>Hours:</h7><input type="number" name="hours" value={this.state.hours} onChange={this.handleChange} required />    
                            <h7 style={{ backgroundColor: "powderblue" }}>Mins:</h7><input type="number" name="mins" value={this.state.mins} onChange={this.handleChange} required />  
                            &nbsp;<input type="submit" className="btn btn-danger" />
                            </form>
                        </li>
                       {!isUserVerified && <li><h7 style={{ backgroundColor: "powderblue" }}>Email Not Verified</h7></li>}
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                    {!isUserLoggedIn && <li><Link className="nav-link" to="/signup"  onClick={AuthenticationForApiService.logout}>Sign Up</Link></li>}
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login" >Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationForApiService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default HeaderComponent