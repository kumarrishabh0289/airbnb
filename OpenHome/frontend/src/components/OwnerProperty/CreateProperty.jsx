import React, { Component } from "react";
// import { Link } from "react-router-dom";
// import AuthenticationForApiService from "../OpenHome/AuthenticationForApiService.js";
import { API_URL } from "../../Constants";
import axios from "axios";
class CreateProperty extends Component {
  constructor(props) {
    super(props);
    this.state = {
      lat: 0.0,
      lon: 0.0,
      ownerId: "",
      propertyType: "Apartment",
      sharingType: "Full",
      street: "",
      city: "",
      state: "",
      zipcode: "",
      phone: "",
      totalFootage: "",
      roomSquareFootage: "",
      showerIncluded: "true",
      bathIncluded: "true",
      numberOfRooms: "1",
      weekdayPrice: "",
      weekendPrice: "",
      picture: "",
      propertyDescription: "",
      parking: "true",
      parkingFee: "",
      wifi: "true",
      sun: false,
      mon: false,
      tue: false,
      wed: false,
      thu: false,
      fri: false,
      sat: false,
      status: ""
    };
    this.getLocation = this.getLocation.bind(this);
    this.showPosition = this.showPosition.bind(this);
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

  componentDidMount = () => {
    this.getLocation();
}




  changeHandler = (name, e) => {
    this.setState({
      [name]: e.target.value
    });
  };

  sunCheckboxHandler = () => {
    this.setState({
      sun: !this.state.sun
    });
  };

  monCheckboxHandler = () => {
    this.setState({
      mon: !this.state.mon
    });
  };

  tueCheckboxHandler = () => {
    this.setState({
      tue: !this.state.tue
    });
  };

  wedCheckboxHandler = () => {
    this.setState({
      wed: !this.state.wed
    });
  };

  thuCheckboxHandler = () => {
    this.setState({
      thu: !this.state.thu
    });
  };

  friCheckboxHandler = () => {
    this.setState({
      fri: !this.state.fri
    });
  };

  satCheckboxHandler = () => {
    this.setState({
      sat: !this.state.sat
    });
  };

  createProperty = e => {
    e.preventDefault();
    var header = { "Content-Type": "application/JSON" };
    let user = sessionStorage.userId;
    // var i;
    // let rooms = [];
    // for (i = 0; i < this.state.numberOfRooms; i++) {
    //   rooms.push({
    //     weekdayPrice: this.state.weekdayPrice,
    //     weekendPrice: this.state.weekendPrice,
    //     roomSquareFootage: this.state.roomSquareFootage,
    //     showerIncluded: this.state.showerIncluded,
    //     bathIncluded: this.state.bathIncluded
    //   });
    //}
    const data = {
      owner: { id: user },
      propertyType: this.state.propertyType,
      sharingType: this.state.sharingType,
      streetName: this.state.street,
      city: this.state.city,
      state: this.state.state,
      zipcode: this.state.zipcode,
      phone: this.state.phone,
      totalSquareFootage: this.state.totalSquareFootage,
      numberOfRooms: this.state.numberOfRooms,
      picture: this.state.picture,
      propertyDescription: this.state.propertyDescription,
      parking: this.state.parking,
      parkingFee: this.state.parkingFee,
      wifi: this.state.wifi,
      weekdayPrice: this.state.weekdayPrice,
      weekendPrice: this.state.weekendPrice,
      roomSquareFootage: this.state.roomSquareFootage,
      showerIncluded: this.state.showerIncluded,
      bathIncluded: this.state.bathIncluded,
      sun: this.state.sun,
      mon: this.state.mon,
      tue: this.state.tue,
      wed: this.state.wed,
      thu: this.state.thu,
      fri: this.state.fri,
      sat: this.state.sat,
      mobileNumber: this.state.mobileNumber,
      status: "Created"
    };
    console.log(data);
    if (this.state.street === "") {
      alert("Street Address is Empty");
    } else if (this.state.city === "") {
      alert("City is Empty");
    } else if (this.state.zipcode === "") {
      alert("ZipCode is Empty");
    } else if (this.state.phone === "") {
      alert("Phone Number is Empty");
    } else if (this.state.totalSquareFootage === "") {
      alert("Total Square Footage is Empty");
    } else if (this.state.weekdayPrice === "") {
      alert("Weekday Price/day is Empty");
    } else if (this.state.weekendPrice === "") {
      alert("Weekend Price/day is Empty");
    } else if (this.state.picture === "") {
      alert("Property Picture not added");
    } else if (this.state.propertyDescription === "") {
      alert("Property Description is Empty");
    } else {
      axios.post(API_URL + "/property/add", data, header).then(response => {
        if (response.status === 201) {
          this.setState({ status: "Success" });
          this.props.history.push(`/hostdashboard/${sessionStorage.userName}`);
        } else {
          console.log(response);
          alert("Error in creating property");
          this.setState({ status: "Error in creating property" });
        }
      });
    }
  };
  componentWillMount() {}

  render() {
    return (
      <div>
        <div class="container-fluid">
          <br />
          <br />
          <div class="row">
            <div class="col-sm-1 col-md-1"></div>

            <div
              class="col-sm-5 col-md-5"
              style={{
                backgroundColor: "white",
                opacity: 1,
                filter: "Alpha(opacity=50)",
                borderRadius: "10px"
              }}
            >
              <h1>Tell us more about your home</h1>

              <form onSubmit={this.createProperty}>
                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <br />
                    <div class="form-group">
                      <label for="property type">
                        <h5>What kind of place do you want to put for rent</h5>
                      </label>
                      <div class="row">
                        <div class="col-sm-6 col-md-6">
                          <div class="form-group">
                            <label for="where">
                              <h5>Property Type</h5>
                            </label>
                            <div class="form-group">
                              <select
                                class="form-control"
                                id="propertyType"
                                onChange={this.changeHandler.bind(
                                  this,
                                  "propertyType"
                                )}
                              >
                                <option value="Apartment">Apartment</option>
                                <option value="Condo">Condo</option>
                                <option value="Bed and Breakfast">
                                  Bed and Breakfast
                                </option>
                                <option value="Hostel">Hostel</option>
                                <option value="House">House</option>
                                <option value="Townhouse">Townhouse</option>
                                <option value="Villa">Villa</option>
                                <option value="Farmhouse">Farmhouse</option>
                                <option value="Cottage">Cottage</option>
                                <option value="PentHouse">PentHouse</option>
                              </select>
                            </div>
                          </div>
                        </div>
                        <div class="col-sm-6 col-md-6">
                          <div class="form-group">
                            <label for="where">
                              <h5>Sharing Type</h5>
                            </label>
                            <div class="form-group">
                              <select
                                class="form-control"
                                id="sharingType"
                                onChange={this.changeHandler.bind(
                                  this,
                                  "sharingType"
                                )}
                              >
                                <option value="Full">Full</option>
                                <option value="Private">Private</option>
                              </select>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {this.state.sharingType === "Private" && (
                  <div class="row">
                    <div class="col-6 col-md-6">
                      <div class="form-group">
                        <label for="where">
                          <h5>Rooms Square Footage</h5>
                        </label>
                        <input
                          type="text"
                          class="form-control"
                          id="roomSquareFootage"
                          placeholder="Total Square Footage in sq ft"
                          onChange={this.changeHandler.bind(
                            this,
                            "roomSquareFootage"
                          )}
                        />
                      </div>
                    </div>

                    <div class="col-sm-6 col-md-6">
                      <div class="form-group">
                        <label for="where">
                          <h5>Shower Included</h5>
                        </label>
                        <div class="form-group">
                          <select
                            class="form-control"
                            id="showerIncluded"
                            onChange={this.changeHandler.bind(
                              this,
                              "showerIncluded"
                            )}
                          >
                            <option value="true">Available</option>
                            <option value="false">Not Available</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-sm-6 col-md-6">
                      <div class="form-group">
                        <label for="where">
                          <h5>Bath Included</h5>
                        </label>
                        <div class="form-group">
                          <select
                            class="form-control"
                            id="bathIncluded"
                            onChange={this.changeHandler.bind(
                              this,
                              "bathIncluded"
                            )}
                          >
                            <option value="true">Available</option>
                            <option value="false">Not Available</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                )}
                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="Address">
                        <h5>Please provide your address</h5>
                      </label>
                      <br></br>
                      <label for="where">
                        <h5>Street</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="street"
                        placeholder="Street Name"
                        onChange={this.changeHandler.bind(this, "street")}
                      />
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="where">
                        <h5>City</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="city"
                        placeholder="City"
                        onChange={this.changeHandler.bind(this, "city")}
                      />
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>State</h5>
                      </label>
                      <div class="form-group">
                        <select
                          class="form-control"
                          id="state"
                          onChange={this.changeHandler.bind(this, "state")}
                        >
                          <option value="Alabama">Alabama</option>
                          <option value="Alaska">Alaska</option>
                          <option value="Arizona">Arizona</option>
                          <option value="Arkansas">Arkansas</option>
                          <option value="California">California</option>
                          <option value="Colorado">Colorado</option>
                          <option value="Connecticut">Connecticut</option>
                          <option value="Delaware">Delaware</option>
                          <option value="Florida">Florida</option>
                          <option value="Georgia">Georgia</option>
                          <option value="Hawaii">Hawaii</option>
                          <option value="Idaho">Idaho</option>
                          <option value="Illinois">Illinois</option>
                          <option value="Indiana">Indiana</option>
                          <option value="Iowa">Iowa</option>
                          <option value="Kansas">Kansas</option>
                          <option value="Kentucky">Kentucky</option>
                          <option value="Louisiana">Louisiana</option>
                          <option value="Maine">Maine</option>
                          <option value="Maryland">Maryland</option>
                          <option value="Massachusetts">Massachusetts</option>
                          <option value="Michigan">Michigan</option>
                          <option value="Minnesota">Minnesota</option>
                          <option value="Mississippi">Mississippi</option>
                          <option value="Missouri">Missouri</option>
                          <option value="Montana">Montana</option>
                          <option value="Nebraska">Nebraska</option>
                          <option value="Nevada">Nevada</option>
                          <option value="New Hampshire">New Hampshire</option>
                          <option value="New Jersey">New Jersey</option>
                          <option value="New Mexico">New Mexico</option>
                          <option value="New York">New York</option>
                          <option value="North Carolina">North Carolina</option>
                          <option value="North Dakota">North Dakota</option>
                          <option value="Ohio">Ohio</option>
                          <option value="Oklahoma">Oklahoma</option>
                          <option value="Oregon">Oregon</option>
                          <option value="Pennsylvania">Pennsylvania</option>
                          <option value="Rhode Island">Rhode Island</option>
                          <option value="South Carolina">South Carolina</option>
                          <option value="South Dakota">South Dakota</option>
                          <option value="Tennessee">Tennessee</option>
                          <option value="Texas">Texas</option>
                          <option value="Utah">Utah</option>
                          <option value="Vermont">Vermont</option>
                          <option value="Virginia">Virginia</option>
                          <option value="Washington">Washington</option>
                          <option value="West Virginia">West Virginia</option>
                          <option value="Wisconsin">Wisconsin</option>
                          <option value="Wyoming">Wyoming</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Zipcode</h5>
                      </label>
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control"
                          id="zipcode"
                          placeholder="Zipcode"
                          onChange={this.changeHandler.bind(this, "zipcode")}
                        />
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="where">
                        <h5>Phone number</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="phone"
                        placeholder="phone"
                        onChange={this.changeHandler.bind(this, "phone")}
                      />
                    </div>
                  </div>
                </div>
                <label for="Address">
                  <h5>Let's gather more information about the property</h5>
                </label>
                <br></br>
                <div class="row">
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Total Square Footage</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="totalSquareFootage"
                        placeholder="Total Square Footage in sq ft"
                        onChange={this.changeHandler.bind(
                          this,
                          "totalSquareFootage"
                        )}
                      />
                    </div>
                  </div>
                  {this.state.sharingType === "Full" && (
                    <div class="col-sm-6 col-md-6">
                      <div class="form-group">
                        <label for="where">
                          <h5>Number of Rooms</h5>
                        </label>
                        <div class="form-group">
                          <select
                            class="form-control"
                            id="numberOfRooms"
                            onChange={this.changeHandler.bind(
                              this,
                              "numberOfRooms"
                            )}
                          >
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="1">3</option>
                            <option value="2">4</option>
                            <option value="1">5</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
                <div class="row">
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Weekday Price/day</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="weekdayPrice"
                        placeholder="weekday price for each room (per day)"
                        onChange={this.changeHandler.bind(this, "weekdayPrice")}
                      />
                    </div>
                  </div>
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Weekend Price/day</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="weekendPrice"
                        placeholder="weekend price for each room (per day)"
                        onChange={this.changeHandler.bind(this, "weekendPrice")}
                      />
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="Address">
                        <h5>
                          Provide picture and description of the place to give a
                          glimpse to user
                        </h5>
                      </label>
                      <br></br>
                      <label for="where">
                        <h5>Picture url</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="picture"
                        placeholder="Picture url"
                        onChange={this.changeHandler.bind(this, "picture")}
                      />
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="where">
                        <h5>Description</h5>
                      </label>
                      <textarea
                        type="text"
                        class="form-control"
                        id="propertyDescription"
                        placeholder="Description of place"
                        onChange={this.changeHandler.bind(
                          this,
                          "propertyDescription"
                        )}
                      />
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Parking</h5>
                      </label>
                      <div class="form-group">
                        <select
                          class="form-control"
                          id="parking"
                          onChange={this.changeHandler.bind(this, "parking")}
                        >
                          <option value="true">Available</option>
                          <option value="false">Not Available</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="Address">
                        <h5>Parking fee per day</h5>
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="parkingFee"
                        placeholder="Parking Fee per day"
                        onChange={this.changeHandler.bind(this, "parkingFee")}
                      />
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                      <label for="where">
                        <h5>Internet/Wifi</h5>
                      </label>
                      <div class="form-group">
                        <select
                          class="form-control"
                          id="wifi"
                          onChange={this.changeHandler.bind(this, "wifi")}
                        >
                          <option value="true">Available</option>
                          <option value="false">Not Available</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <label for="where">
                        <h5>Availability Days</h5>
                      </label>
                      <div class="form-group">
                        <fieldset>
                          <label>
                            <input
                              type="checkbox"
                              name="sun"
                              value="true"
                              onChange={this.sunCheckboxHandler.bind()}
                            />{" "}
                            Sunday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="mon"
                              value="true"
                              onChange={this.monCheckboxHandler.bind()}
                            />{" "}
                            Monday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="tue"
                              value="true"
                              onChange={this.tueCheckboxHandler.bind()}
                            />{" "}
                            Tuesday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="wed"
                              value="true"
                              onChange={this.wedCheckboxHandler.bind()}
                            />{" "}
                            Wednesday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="thu"
                              value="true"
                              onChange={this.thuCheckboxHandler.bind()}
                            />{" "}
                            Thursday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="fri"
                              value="true"
                              onChange={this.friCheckboxHandler.bind()}
                            />{" "}
                            Friday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="sat"
                              value="true"
                              onChange={this.satCheckboxHandler.bind()}
                            />{" "}
                            Saturday
                          </label>
                        </fieldset>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <br />
                      <h4>Latitude :{this.state.lat}, Longitude: {this.state.lon}</h4>
                      
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-sm-12 col-md-12">
                    <div class="form-group">
                      <br />
                      <input
                        type="submit"
                        class="form-control btn btn-danger"
                      />
                      <br />
                      <br />
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateProperty;
