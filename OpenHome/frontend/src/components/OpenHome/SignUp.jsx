import React, { Component } from 'react'
// import { Link } from 'react-router-dom'
// import AuthenticationForApiService from './AuthenticationForApiService.js'
import axios from 'axios';
import { API_URL } from '../../Constants'

class SignUp extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: sessionStorage.getItem("googleName"),
            password: "",
            role: "",
            email: sessionStorage.getItem("googleEmail"),
            signup_status: "",
            hasFailed: false,
            showSuccessMessage: false,
            cardNumber: "",
            cvv: "",
            expiryDate: "",


        }
        this.submitSignUp = this.submitSignUp.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentWillMount() {

    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    submitSignUp = (e) => {
        let role = ""
        if (this.state.email.includes("@sjsu.edu")) {
            role = "host"
        }
        else {
            role = "user"
        }

        console.log("submit login called")
      //  var headers = new Headers();
        //prevent page from refresh
        e.preventDefault();
        const data = {
            email: this.state.email,
            password: this.state.password,
            name: this.state.name,
            role: role,
            verification: "no",
            cardNumber: this.state.cardNumber,
            cvv: this.state.cvv,
            expiryDate: this.state.expiryDate,
        }
        console.log("data", data)
        //set the with credentials to true
        //axios.defaults.withCredentials = true;
        //make a post request with the user data
        axios.post(API_URL + "/persons/", data)
            .then((response) => {
                console.log("Status Code : ", response.status);
                if (response.status === 201) {

                    console.log(response.data);
                    this.setState({

                        signup_status: response.data.message,
                        showSuccessMessage: true
                    })
                } else {
                    console.log(response.data.error);
                    this.setState({


                        signup_status: response.data.error,
                        hasFailed: true
                    })
                }
            });
    }




    render() {
        return (
            <div>
                <div className="container-fluid">
                    <br />
                    <br />
                    <div className="row" >
                        <div className="col-sm-1 col-md-1"></div>

                        <div className="col-sm-5 col-md-5" style={{ backgroundColor: "white", opacity: .9, filter: "Alpha(opacity=90)", borderRadius: '10px' }}>

                            <h3>
                                Sign Up  {!(sessionStorage.getItem("googleEmail") === null) && <div>Via Google.</div>}
                            </h3>
                            {!(sessionStorage.getItem("googleEmail") === null) && <p>Your Account is Not with Us. Please Enter Mandatory Details</p>}
                            <form onSubmit={this.submitSignUp}>
                                <div className="row" >

                                    <div className="col-sm-12 col-md-12">
                                        <br />
                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Email ID</h5></label>
                                           <input type="email" className="form-control" name="email" id="email" placeholder="Your Email" value={this.state.email} onChange={this.handleChange} required />
                                           
                                        </div>

                                    </div>
                                    <div className="col-sm-1 col-md-1">

                                    </div>

                                </div>
                                <div className="row" >

                                    <div className="col-sm-6 col-md-6">

                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Name</h5></label>
                                            <input type="text" className="form-control" name="name" id="name" placeholder="Your Name" value={this.state.name} onChange={this.handleChange} required />

                                        </div>

                                    </div>
                                    <div className="col-sm-6 col-md-6">

                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Password</h5></label>
                                            <input type="password" className="form-control" name="password" id="password" placeholder="password" value={this.state.password} onChange={this.handleChange} required />
                                        </div>

                                    </div>
                                    <div className="col-sm-1 col-md-1">

                                    </div>

                                </div>
                                <div className="row" >
                                    <h4>
                                        <hr /><br />
                                        Add Payment Method <img src="payment.jpg" alt=""></img>
                                    </h4>

                                    <div className="col-sm-12 col-md-12">

                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Card Number</h5></label>
                                            <input type="text" className="form-control" name="cardNumber" id="cardNumber" placeholder="Card Number" value={this.state.cardNumber} onChange={this.handleChange} pattern="[1-9]{1}[0-9]{15}" required />
                                        </div>

                                    </div>

                                </div>
                                <div className="row" >

                                    <div className="col-sm-6 col-md-6">

                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Card Expiry Date</h5></label>
                                            <input type="date" className="form-control" name="expiryDate" id="expiryDate" placeholder="Card Expiry Date" value={this.state.expiryDate} onChange={this.handleChange} required />

                                        </div>

                                    </div>
                                    <div className="col-sm-6 col-md-6">

                                        <div className="form-group">
                                            <label htmlFor="where"><h5>Card Cvv</h5></label>
                                            <input type="text" className="form-control" name="cvv" id="cvv" placeholder="cvv" value={this.state.cvv} onChange={this.handleChange} pattern="[0-9]{3}" required />
                                        </div>

                                    </div>
                                    <div className="col-sm-1 col-md-1">

                                    </div>

                                </div>


                                <div className="row" >

                                    <div className="col-sm-12 col-md-12">
                                        <div className="form-group">

                                            <br />
                                            <input type="submit" className="form-control btn btn-danger" />
                                            <br />
                                            <br />
                                        </div>
                                    </div>

                                    <br />
                                    {this.state.hasFailed && <div className="alert alert-warning">User Creation Failed Check console for More Info.</div>}
                                    {this.state.showSuccessMessage && <div className="alert alert-warning">User Created Successfully</div>}
                                    <br />

                                </div>
                            </form>
                        </div>

                    </div>


                </div>
            </div>
        )
    }
}

export default SignUp