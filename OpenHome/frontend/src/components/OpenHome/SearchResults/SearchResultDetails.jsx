// export default SearchResults;
import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios';
import { Redirect } from 'react-router';
import { API_URL } from "../../../Constants";
import {getCurrentDate} from '../BookingConfirmation/Util'

class SearchResultDetails extends Component {

    constructor(props) {
        super(props)

        this.state = {
            lat: 0.0,
            lon: 0.0,
            welcomeMessage: 'Hey You Are Authorized',
            responseData: '',
            responseData1: '',
            bookingPrice: '',
            rating:'',
            showSuccessMessage:false
            //     moment: moment()
        }

        this.SearchButton = this.SearchButton.bind(this);
        this.BookButton = this.BookButton.bind(this);
        this.getLocation = this.getLocation.bind(this);
        this.showPosition = this.showPosition.bind(this);
        this.submitRating = this.submitRating.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }
    getLocation() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(this.showPosition);
        } else {
          console.log("Geolocation is not supported by this browser.")
        }
      }
      
       showPosition(position) {
          this.setState({
              lat: parseFloat(position.coords.latitude),
              lon: parseFloat(position.coords.longitude)
          })
      }
      submitRating = (e) => {
        
        console.log("submit login called")
        console.log("rating", this.state.rating)
      //  var headers = new Headers();
        //prevent page from refresh
        e.preventDefault();
        
        const data = {
            propertyId:this.state.responseData.propertyId,
            review:this.state.rating,
        }
        console.log("data", data)
        //set the with credentials to true
        //axios.defaults.withCredentials = true;
        //make a post request with the user data
        axios.post(API_URL + "/review/add", data)
            .then((response) => {
                console.log("Status Code : ", response.status);
                if (response.status === 201) {

                    console.log(response.data);
                    this.setState({

                        showSuccessMessage: true
                    })
                } else {
                    console.log(response.data.error);
                    this.setState({


                        hasFailed: true
                    })
                }
            });
            
    }

    componentDidMount() {
        this.getLocation();


        axios.get(API_URL + `/property/${this.props.match.params.propertyId}`)
            .then(response => {
                console.log("Status Code : ", response.status);
                if (response.status === 200) {
                    this.setState({
                        responseData: response.data
                    })

                    var data = JSON.parse(localStorage.getItem('product_details'));
                    var startdate = data.startDate;
                    var enddate = data.endDate;
                    var myDate = new Date(startdate);
                    var myDate1 = new Date(enddate);
                    //apply states and do the calculations


                    var loop = new Date(myDate);
                    var newDate1 = loop.setDate(loop.getDate() + 1);
                    loop = new Date(newDate1);
                    var newDate2 = myDate1.setDate(myDate1.getDate() + 1);
                    myDate1 = new Date(newDate2);
                    var weekdayCnt = 0, weekendCnt = 0;
                    while (loop < myDate1) {
                        var newDate = loop.setDate(loop.getDate() + 1);
                        loop = new Date(newDate);
                        console.log("Date of loop ",loop);
                        console.log("Day of loop ",loop.getDay());

                        if (loop.getDay() === 6 || loop.getDay() === 0)
                            weekendCnt++;
                        else
                            weekdayCnt++;
                    }

                    console.log("weekendCnt",weekendCnt)
                    console.log("weekdayCnt",weekdayCnt)

                    console.log("weekday price",this.state.responseData.weekdayPrice)
                    console.log("weekend price",this.state.responseData.weekendPrice)
                    var finalp = 0;
                    finalp = weekdayCnt * this.state.responseData.weekdayPrice;
                    finalp = finalp + weekendCnt * this.state.responseData.weekendPrice;
                    this.setState({
                        bookingPrice: finalp
                    })
                    if (!response.data) {
                        alert("No Available Properties")
                    }
                }
                else {
                    this.setState({
                        flag: false
                    })
                }
            })
            .catch(err => {
                alert(err);
            });
    }

    SearchButton = (e) => {
        var data = JSON.parse(localStorage.getItem('product_details'));

        axios.post(API_URL + `/search/property`, data)
            .then(response => {
                console.log("Status Code : ", response.status);
                if (response.status === 200) {
                    this.setState({
                        responseData1: response.data//,
                    })
                    console.log(response);
                    if (!response.data) {
                        alert("No Available Properties")
                    }
                }
                else {
                    this.setState({
                        flag: false
                    })
                }
            })
            .catch(err => {
                alert(err);
            });
    }


    BookButton = (e) => {
        e.preventDefault();
        var data = JSON.parse(localStorage.getItem('product_details'));
        console.log(data)

        var header = { "Content-Type": "application/JSON" };

        const dataReservation = {
            bookedPrice: this.state.bookingPrice,
            bookedPriceWeekend: this.state.responseData.weekendPrice,
            bookedPriceWeekday: this.state.responseData.weekdayPrice,
            bookingDate: getCurrentDate(),
            startDate: data.startDate,
            endDate: data.endDate,
            guestId: sessionStorage.userId,
            propertyId: this.props.match.params.propertyId,
            address: this.state.responseData.streetName+", "+this.state.responseData.city+", "+this.state.responseData.zipcode,
            description: this.state.responseData.propertyDescription
        };
        console.log("dataReservation",dataReservation)
        axios.post(API_URL + "/reservation/new", dataReservation, header).then(response => {
            console.log("response",response)
            if (response.status === 201) {
                this.setState({ status: "Success" });
                this.props.history.push(`/welcomeuser/${sessionStorage.userName}`)
               
            } else {
                console.log(response);
                alert("Error in creating property");
                this.setState({ status: "Error in creating property" });
            }
        });
    };

       


render() {
    let redirectvar = null
    if (this.state.responseData1) {
        console.log("should redirect")
        console.log(this.state.responseData1);
        redirectvar = <Redirect to={{
            pathname: '/search/searchResults',
            state: {
                responseData1: this.state.responseData1
            }
        }} />
    }



    let displayImage = null;
    if (!this.state.responseData.picture) {
        displayImage = (
            <div>
                <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx" height="300" width="420" alt="description" />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
    else if (this.state.responseData.picture) {
        displayImage = (
            <div>
                <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src={this.state.responseData.picture} height="300" width="420" alt="description" />
                        </div>
                    </div>
                </div>
            </div>
        )
    }



    return (
        <>
            <div id="mainbody">
                {redirectvar}


                <div className="container main-content">
                    <div class="property_details">
                        {displayImage}
                        <br/>
                          
                  <iframe title="maplocation" src={"https://maps.google.com/maps?q="+this.state.lat+","+this.state.lon+"&z=15&output=embed"} width="300" height="270" frameborder="0" style={{border:0}}></iframe>
                
                        <hr />
                        <div class="row" >

                            <div class="col-sm-12 col-md-12">

                                <h3>{this.state.responseData.propertyDescription}</h3>
                                <p class="info"> <b> Address : </b> {this.state.responseData.streetName}, {this.state.responseData.city}, {this.state.responseData.zipcode}</p>
                                <p class="info"> <b> Property Type : </b> {this.state.responseData.propertyType}</p>
                                <p class="info"> <b> Sharing Type : </b> {this.state.responseData.sharingType} </p>
                                <p class="info">  <b>Total Square Footage :</b> {this.state.responseData.totalSquareFootage}</p>
                                <p class="info">  <b>Number Of Rooms :</b> {this.state.responseData.numberOfRooms}</p>
                                <p class="price"> ${this.state.responseData.weekendPrice} per  weekend night</p>
                                <p class="price"> ${this.state.responseData.weekdayPrice} per  weekday night</p>
                                </div>
                            </div>
                            <div class="row" >

                            <div class="col-sm-7 col-md-7">
                                {(!(sessionStorage.getItem("authenticatedUser") === null) && (sessionStorage.getItem("userRole") === "user") && (sessionStorage.getItem("verified") === "yes")) && <div class="price"> Give rating to this property <br/>
                                <form onSubmit={this.submitRating}>
                                
                                <input  type="number" name="rating" id="rating" value={this.state.rating} onChange={this.handleChange} min="1" max="5" placeholder="number between 1-5" style={{width: "15em"}}/>
                                &nbsp;&nbsp;&nbsp;
                                <input type="submit"  />
                                </form>
                                </div>}
                                <hr />
                                <button class="btn btn-danger" name="BookButton" onClick={this.SearchButton} >
                                    <span>Previous Page</span>
                                </button>
                                &nbsp;
                                    {(!(sessionStorage.getItem("authenticatedUser") === null) && (sessionStorage.getItem("userRole") === "user") && (sessionStorage.getItem("verified") === "yes")) && <button class="btn btn-danger" name="BookButton" onClick={this.BookButton} > <span>Book Property</span></button>}
                                {(sessionStorage.getItem("authenticatedUser") === null) && <div><Link to="/login">Login</Link> to Continue Booking....</div>}
                                {(sessionStorage.getItem("verified") === "no") && <div>Please Verify your Email to Start Booking.</div>}
                                {this.state.showSuccessMessage && <div className="alert alert-warning">Rating was successfully registered</div>}
                           </div>
                           </div>
                    </div>
                </div>

            </div>
            <br />
        </>
    )
}



}


export default SearchResultDetails
