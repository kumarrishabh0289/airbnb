import React, { Component } from "react";
//import { Link } from 'react-router-dom'
import axios from "axios";
import { Redirect } from "react-router";

class SearchResultDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      welcomeMessage: "Hey You Are Authorized",
      responseData: "",
      responseData1: ""
    };
    this.SearchButton = this.SearchButton.bind(this);
    this.BookButton = this.BookButton.bind(this);
  }

  componentDidMount() {
    var data = JSON.parse(localStorage.getItem("product_details"));
    var startdate = data.startDate;
    var enddate = data.endDate;
    var myDate = new Date(startdate);
    var myDate1 = new Date(enddate);
    console.log(myDate.getDay());
    console.log(myDate1.getDay());
    //apply states and do the calculations
    if (
      myDate.getDay() === 6 ||
      myDate.getDay() === 5 ||
      myDate1.getDay() === 5 ||
      myDate.getDay() === 6
    )
      console.log("weekend");
    else console.log("weekday");
    console.log(data);
    console.log(startdate, enddate);
    axios
      .get(
        `http://localhost:8181/property/${this.props.match.params.propertyId}`
      )
      .then(response => {
        console.log("Status Code : ", response.status);
        if (response.status === 200) {
          this.setState({
            responseData: response.data
          });
          console.log("ress", response);
          if (!response.data) {
            alert("No Available Properties");
          }
        } else {
          this.setState({
            flag: false
          });
        }
      })
      .catch(err => {
        alert(err);
      });
  }

  SearchButton = e => {
    var data = JSON.parse(localStorage.getItem("product_details"));
    axios
      .post(`http://localhost:8181/search/property`, data)
      .then(response => {
        console.log("Status Code : ", response.status);
        if (response.status === 200) {
          this.setState({
            responseData1: response.data //,
          });
          console.log(response);
          if (!response.data) {
            alert("No Available Properties");
          }
        } else {
          this.setState({
            flag: false
          });
        }
      })
      .catch(err => {
        alert(err);
      });
  };

  BookButton = e => {
    // var data = JSON.parse(localStorage.getItem('product_details'));
    var data = JSON.parse(localStorage.getItem("product_details"));
    console.log(data);
    const payload = {
      booked_price: 50,
      booked_price_weekend: 10,
      booked_price_weekday: 11,
      booking_date: data.startDate,
      startDate: data.startDate,
      endDate: data.endDate,
      guest: 1,
      propertyId: 1
    };
    console.log("payload", payload);
    axios
      .post(`http://localhost:8181/reservation/new`, payload)
      .then(response => {
        console.log("Status Code : ", response.status);
        if (response.status === 200) {
          this.setState({
            responseData1: response.data
          });
          console.log(response);
          if (!response.data) {
            alert("No Available Properties");
          }
        } else {
          this.setState({
            flag: false
          });
        }
      })
      .catch(err => {
        alert(err);
      });
  };

  render() {
    let redirectvar = null;
    if (this.state.responseData1) {
      console.log("should redirect");
      console.log(this.state.responseData1);
      redirectvar = (
        <Redirect
          to={{
            pathname: "/search/searchResults",
            state: {
              responseData1: this.state.responseData1
            }
          }}
        />
      );
    }
    let displayImage = null;
    if (!this.state.responseData.picture) {
      displayImage = (
        <div>
          <div
            id="carouselExampleControls"
            class="carousel slide right-side"
            data-ride="carousel"
          >
            <div class="carousel-inner">
              <div class="carousel-item active">
                {/* <h1>Images Pending</h1> */}
                <img
                  src="https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx"
                  height="300"
                  width="420"
                  alt="description"
                />
              </div>
            </div>
          </div>
        </div>
      );
    } else if (this.state.responseData.picture) {
      displayImage = (
        <div>
          <div
            id="carouselExampleControls"
            class="carousel slide right-side"
            data-ride="carousel"
          >
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img
                  src={this.state.responseData.picture}
                  height="300"
                  width="420"
                  alt="description"
                />
              </div>
            </div>
          </div>
        </div>
      );
    }

    return (
      <>
        <div id="mainbody">
          {redirectvar}
          <div className="container main-content">
            <div class="property_details">
              {displayImage}
              <div class="col-md-7 right-side">
                <hr></hr>
                <h3>{this.state.responseData.propertyDescription}</h3>
                <p class="info">
                  {" "}
                  <b> Address : </b> {this.state.responseData.streetName},{" "}
                  {this.state.responseData.city},{" "}
                  {this.state.responseData.zipcode}
                </p>
                <p class="info">
                  {" "}
                  <b> Property Type : </b>{" "}
                  {this.state.responseData.propertyType}, Sharing Type :{" "}
                  {this.state.responseData.sharingType}, Total Square Footage :{" "}
                  {this.state.responseData.totalSquareFootage}, Number Of Rooms
                  : {this.state.responseData.numberOfRooms}
                </p>
                <p class="info">
                  {" "}
                  <b> Sharing Type : </b> {this.state.responseData.sharingType},
                  Total Square Footage :{" "}
                  {this.state.responseData.totalSquareFootage}, Number Of Rooms
                  : {this.state.responseData.numberOfRooms}
                </p>
                <p class="info">
                  {" "}
                  <b>Total Square Footage :</b>{" "}
                  {this.state.responseData.totalSquareFootage}, Number Of Rooms
                  : {this.state.responseData.numberOfRooms}
                </p>
                <p class="info">
                  {" "}
                  <b>Number Of Rooms :</b>{" "}
                  {this.state.responseData.numberOfRooms}
                </p>
                <p class="price">
                  $ {this.state.responseData.totalSquareFootage} per night
                </p>
                <hr></hr>
                <button
                  class="btn btn-danger"
                  name="BookButton"
                  onClick={this.SearchButton}
                >
                  <span>Previous Page</span>
                </button>
                &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                <button
                  class="btn btn-danger"
                  name="BookButton"
                  onClick={this.BookButton}
                >
                  <span>Book Property</span>
                </button>
              </div>
            </div>
          </div>
        </div>
        <br />
      </>
    );
  }
}

export default SearchResultDetails;
