import React, { Component } from "react";
import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
class WelcomeUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      booking: []
    };
  }

  componentDidMount() {
    let id = sessionStorage.userId;
    axios
      .get(API_URL + `/reservation/guest/${id}`, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => {
        console.log(response.data);
        this.setState({
          booking: this.state.booking.concat(response.data)
        });
      });
  }

  checkIn = (booking) => {
    alert("Check-in for this Booking ID : "+booking.id)
  };

  checkOut = (booking) => {
    alert("Check-Out for this Booking ID : "+booking.id)
  };

  cancel = (booking) => {
    alert("Cancel for this Booking ID : "+booking.id)
  };

  render() {
    return (
      <div className="container" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=100)", borderRadius: '10px' }}>

        <br />
        <h2>Openhome User Dashboard</h2>
        <br />
        <h4>Welcome {this.props.match.params.name}</h4>


        <table className="table">
          <tr>
            <th>Booking ID</th>
            <th>Address</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Check-IN</th>
            <th>Check-Out</th>
            <th>Penalty Reason</th>
            <th>Penalty Amount</th>
            <th>Booking Amount</th>
            <th>Total Payable</th>
            <th>Status</th>
            <th></th>
            <th></th>
            <th></th>

          </tr>
          {this.state.booking.map(booking => {
            return (
              <tr>
                <td>{booking.id}</td>
                <td>{booking.address}</td>
                <td>{booking.description}</td>
                <td>{booking.startDate}</td>
                <td>{booking.endDate}</td>
                <td>{booking.checkInDate}</td>
                <td>{booking.checkOutDate}</td>
                <td>{booking.penaltyReason}</td>
                <td>{booking.penaltyValue}</td>
                <td>{booking.bookedPrice}</td>
                <td>{booking.paymentAmount}</td>
                <td>{booking.status}</td>
                <td> <button onClick={() => this.checkIn(booking)} class="btn btn-primary">Check-In</button></td>
                <td> <button onClick={() => this.checkOut(booking)} class="btn btn-primary">Check-Out</button></td>
                <td> <button onClick={() => this.cancel(booking)} class="btn btn-primary">Cancel</button></td>
              </tr>
            );
          })}
        </table>
      </div>


    );
  }
}

export default WelcomeUser;
