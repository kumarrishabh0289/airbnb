import React, { Component } from 'react';
import { API_URL } from "../../../Constants";
import axios from "axios";
import {getCurrentDate} from './Util.jsx'
// import { Link } from "react-router-dom";
import Draggable from "react-draggable";
//import { Link } from 'react-router-dom'


class BookingConfirmation extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            properties: [],
            welcomeMessage: 'Hey You Are Authorized'
        }
        
        
    }
    bookProperty = e => {
        e.preventDefault();
        var header = { "Content-Type": "application/JSON" };
        
        const data = {
          bookedPrice:sessionStorage.booked_price,
          bookedPriceWeekend:sessionStorage.weekend_price,
          bookedPriceWeekday:sessionStorage.weekday_price,
          bookingDate:getCurrentDate(),
          startDate:sessionStorage.startDate,
          endDate:sessionStorage.endDate,
          guestId:sessionStorage.userId,
          propertyId:sessionStorage.propertyId
        };
        console.log(data);
        axios.post(API_URL + "/reservation/new", data, header).then(response => {
          if (response.status === 201) {
            this.setState({ status: "Success" });
            //this.props.history.push("/property/dashboard");
          } else {
            console.log(response);
            alert("Error in creating property");
            this.setState({ status: "Error in creating property" });
          }
        });
      };
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
            
            <div class="container">
        <div class="body-div">
          <br />
          <h2>Booked Property</h2>
          <br />
          
          <br></br>
          <div class="card-columns">
            {this.state.properties.map(property => {
              return (
                <Draggable>
                  <div>
                    <div class="card text-white bg-dark mb-3">
                      <div class="card-header">{property.name}</div>
                      <div class="card-body">
                        <p class="card-text">
                          <img
                            src={property.picture}
                            height="250"
                            width="300"
                            alt=""
                          />
                          <table>
                            <tr>
                              <th>Property:</th>
                              <td>{property.propertyDescription}</td>
                            </tr>
                          </table>
                        </p>
                      </div>
                    </div>
                  </div>
                </Draggable>
              );
            })}
          </div>
        </div>
      </div>
            )

            </>
        )
    }
}
export default BookingConfirmation