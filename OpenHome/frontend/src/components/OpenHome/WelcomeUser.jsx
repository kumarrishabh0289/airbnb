import React, { Component } from "react";
import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
import Draggable from "react-draggable";
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
  OpenProperty = booking => {
    this.props.history.push(`/booking/${booking.bookingId}`);
  };

  render() {
    return (
      <div class="container">
        <div class="body-div">
          <br />
          <h2>Openhome User Dashboard</h2>
          <br />
          <h4>Welcome {this.props.match.params.name}</h4>
          <br></br>
          <Link to="/property/new">
            <a href="/#" class="btn btn-info btn-lg">
              <span class="glyphicon glyphicon-plus">+</span> Property
            </a>
          </Link>
          <br></br>
          <br></br>
          <div class="card-columns">
            {this.state.booking.map(booking => {
              return (
                <>
                    {/* <table className="table">
                      <tr>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking Price
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                        <th>
                          Booking ID
                        </th>
                      </tr>
                    </table> */}
                </>
              );
            })}
          </div>
        </div>
      </div>
    );
  }
}

export default WelcomeUser;
