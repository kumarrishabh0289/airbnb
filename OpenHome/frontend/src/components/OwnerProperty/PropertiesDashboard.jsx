import React, { Component } from "react";
import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
import Draggable from "react-draggable";
class PropertiesDashboard extends Component {
  constructor(props) {
    super(props);

    this.state = {
      properties: []
    };
  }

  componentDidMount() {
    let id = 5;
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
    return (
      <div class="container">
        <div class="body-div">
          <br />
          <h2>Openhome Dashboard</h2>
          <br />
          <h4>Welcome Host</h4>
          <br></br>
          <Link to="/property/new">
            <a href="/#" class="btn btn-info btn-lg">
              <span class="glyphicon glyphicon-plus">+</span> Property
            </a>
          </Link>
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

export default PropertiesDashboard;
