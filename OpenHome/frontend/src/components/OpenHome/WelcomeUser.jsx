import React, { Component } from "react";
// import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
class WelcomeUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      booking: [],
      msg: "",
      flag: false
    };
  }

  componentDidMount() {
    window.history.pushState(null, document.title, window.location.href);
    window.addEventListener('popstate', function (event){
        window.history.pushState(null, document.title,  window.location.href);
    });


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
    //alert("Check-in for this Booking ID : "+booking.id)
    var data = {
      "reservationId": booking.id

    };

    axios.post(API_URL + `/reservation/checkin`, data)
      .then(response => {
        let id = sessionStorage.userId;
        axios
          .get(API_URL + `/reservation/guest/${id}`, {
            headers: { "Content-Type": "application/json" }
          })
          .then(response => {
            
            this.setState({
              booking: response.data
            });
          });
        console.log("Status Code : ", response.status);
        if (response.status === 200) {
          this.setState({
            msg: "Check-in Successful",
            flag: true
          })
          console.log(response);
          if (!response.data) {
            // alert("No Available Properties")
          }
        }
        else {
          this.setState({
            msg: "Unable to Check-in",
            flag: true
          })
        }
      })
      .catch(err => {
        let id = sessionStorage.userId;
        axios
          .get(API_URL + `/reservation/guest/${id}`, {
            headers: { "Content-Type": "application/json" }
          })
          .then(response => {
            console.log(response.data);
            this.setState({
              booking: response.data
            });
          });
        //alert(err);
        this.setState({
          msg: "Unable to Check-in",
          flag: true
        })
      });
  };

  checkOut = (booking) => {
    alert("Check-Out for this Booking ID : " + booking.id)
  };

  cancel = (booking) => {
    alert("Cancel for this Booking ID : " + booking.id)
  };

  render() {
    return (
      <div style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=100)", borderRadius: '10px' }}>

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

                <td>{booking.state}</td>
                <td> <button onClick={() => this.checkIn(booking)} class="btn btn-primary">Check-In</button></td>
                <td> <button onClick={() => this.checkOut(booking)} class="btn btn-primary">Check-Out</button></td>
                <td> <button onClick={() => this.cancel(booking)} class="btn btn-primary">Cancel</button></td>
              </tr>
            );
          })}
        </table>
        <h4 style={{ backgroundColor: "powderblue" }}>{this.state.flag && <>{this.state.msg}</>}</h4>
      </div>


    );
  }
}

export default WelcomeUser;
