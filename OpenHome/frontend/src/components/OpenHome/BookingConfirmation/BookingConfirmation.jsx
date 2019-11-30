import React, { Component } from 'react';
import { API_URL } from "../../../Constants";
import axios from "axios";
//import { Link } from 'react-router-dom'


class BookingConfirmation extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            properties: [],
            welcomeMessage: 'Hey You Are Authorized'
        }
        
        
    }
    componentDidMount(){
        let id = sessionStorage.propertyId
        axios
      .get(API_URL + `/property/${id}`, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => {
        console.log("response.data",response.data);
        this.setState({
          properties: this.state.properties.concat(response.data)
        });
        console.log(this.state.properties[0].owner.name);
      });
    }

    render() {
        return (
            <>
                <h1>Welcome!</h1>
                <div className="container">
                    Property ID: {sessionStorage.propertyId}<br/>
                End Date: {sessionStorage.endDate}<br/>
                Start Date : {sessionStorage.startDate}<br/>
                Required Rooms: {sessionStorage.booking_total_room}<br/>

                   
                   
                </div>
                <div className="container">
                    
                       
                </div>
                <div className="container">
                    
                </div>

            </>
        )
    }



}


export default BookingConfirmation