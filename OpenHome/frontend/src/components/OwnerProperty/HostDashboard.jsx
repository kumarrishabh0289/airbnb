import React, { Component } from "react";
import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
import Draggable from "react-draggable";
import AuthenticationForApiService from '../OpenHome/AuthenticationForApiService'
class HostDashboard extends Component {
  constructor(props) {
    super(props);

    this.state = {
      properties: []
    };
  }

  componentDidMount() {
    let id = sessionStorage.userId;
    axios
      .get(API_URL + `/property/owner/${id}`, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => {
        console.log(response.data);
        this.setState({
          properties: this.state.properties.concat(response.data)
        });
      });
  }
  OpenProperty = property => {
    this.props.history.push(`/property/${property.propertyId}`);
  };

  render() {
    const isUserVerified = AuthenticationForApiService.isUserVerified();
    return (
      <div class="container">
        <div class="body-div">
          <br />
          <h2>Openhome Host Dashboard</h2>
          <br />
          <h4>Welcome {this.props.match.params.name}</h4>
          <h7><Link className="nav-link" to="/hostbilling"  >Monthly Billing Overview</Link></h7>
          <br></br>
          {isUserVerified && <li><h7 style={{ backgroundColor: "powderblue" }}><Link to="/property/new">
            <a href="/#" class="btn btn-info btn-lg">
              <span class="glyphicon glyphicon-plus">+</span> Property
            </a>
          </Link></h7></li>}
          
          <br></br>
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
                      <div class="card-footer">
                        <button
                          onClick={() => this.OpenProperty(property)}
                          class="btn btn-primary"
                        >
                          Open Property
                        </button>
                        <br /> <br />
                      </div>
                    </div>
                  </div>
                </Draggable>
              );
            })}
          </div>
        </div>
      </div>
    );
  }
}

export default HostDashboard;
