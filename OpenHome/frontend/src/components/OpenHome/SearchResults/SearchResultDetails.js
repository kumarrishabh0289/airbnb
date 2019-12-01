import React, { Component } from 'react'
//import { Link } from 'react-router-dom'
import axios from 'axios';

class SearchResultDetails extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            welcomeMessage: 'Hey You Are Authorized',
            responseData:''
        }
        
        
    }

    componentDidMount(){

        axios.get(`http://localhost:8181/property/${this.props.match.params.propertyId}`)
        .then(response => {
            console.log("Status Code : ",response.status);
            if(response.status === 200){
                this.setState({
                    responseData:response.data//,
                })
                console.log("ress",response);
                if(!response.data){
                    alert("No Available Properties")
                }
            }
            else{
                this.setState({
                    flag : false
                })
            }
        })
        .catch(err =>{
            alert(err);
        });

    }

    render() {

        let displayImage = null;           
        if(!this.state.responseData.picture)
        {
            displayImage = (
                <div>

                    <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                            {/* <h1>Images Pending</h1> */}
                                <img src = "https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx" height="300" width="420" alt="description"/>
                            </div>
                        </div>
                     </div>
                </div>
            )
        }
        else if(this.state.responseData.picture)
        {
            displayImage = (
                <div>
                
                    <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src = {this.state.responseData.picture} height="300" width="420" alt="description"/>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }

        return (
            <>
                <div id="mainbody">
                    <div className="container main-content">
                        <div class="property_details">
                            {displayImage}

                            <div class="col-md-7 right-side">
                                <hr></hr>
                                <h3>{this.state.responseData.propertyDescription}</h3>
                                <p class="info"> <b> Address : </b> {this.state.responseData.streetName}, {this.state.responseData.city}, {this.state.responseData.zipcode}</p>
                                <p class="info"> <b> Property Type : </b> {this.state.responseData.propertyType}, Sharing Type : {this.state.responseData.sharingType}, Total Square Footage : {this.state.responseData.totalSquareFootage}, Number Of Rooms : {this.state.responseData.numberOfRooms}</p>
                                <p class="info"> <b> Sharing Type : </b> {this.state.responseData.sharingType}, Total Square Footage : {this.state.responseData.totalSquareFootage}, Number Of Rooms : {this.state.responseData.numberOfRooms}</p>
                                <p class="info">  <b>Total Square Footage :</b> {this.state.responseData.totalSquareFootage}, Number Of Rooms : {this.state.responseData.numberOfRooms}</p>
                                <p class="info">  <b>Number Of Rooms :</b> {this.state.responseData.numberOfRooms}</p>
                                <p class="price">$ {this.state.responseData.Tariff} per night</p>
                                <hr></hr>
                                <button class="btn btn-danger" name="BookButton" >
                                    <span>Previous Page</span>
                                </button>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="btn btn-danger" name="BookButton" >
                                    <span>Book Property</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
            </>
        )
    }



}


export default SearchResultDetails