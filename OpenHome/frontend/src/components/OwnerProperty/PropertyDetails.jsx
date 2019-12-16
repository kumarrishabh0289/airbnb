import React, { Component } from "react";
//import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
//import Draggable from "react-draggable";

class PropertyDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      
      properties: [],
      sun: false,
      mon: false,
      tue: false,
      wed: false,
      thu: false,
      fri: false,
      sat: false,
      agree: false,
      changed: false,
      weekdayp: '',
      weekendp :''
    };
   
  }
 



  onChange (event) {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

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

  agreeCheckboxHandler = () => {
    this.setState({
      agree: !this.state.agree
    });
  };

  componentDidMount() {
   
    console.log(this.props.match.params.propertyId);
    axios
      .get(API_URL + `/property/${this.props.match.params.propertyId}`, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => {
      //  console.log(response.data);
        this.setState({
          properties: response.data
        });
        this.setState({
          mon : this.state.properties.mon,
          tue : this.state.properties.tue,
          wed : this.state.properties.wed,
          thu : this.state.properties.thu,
          fri : this.state.properties.fri,
          sat : this.state.properties.sat,
          sun : this.state.properties.sun,
          weekdayp: this.state.properties.weekdayPrice,
          weekendp: this.state.properties.weekendPrice,
        });

        console.log("Prop",this.state.properties);
       // console.log("properties",this.state.properties)
      });
  }

  ChangeAvailability = (e) => {
    e.preventDefault();
    var propertyDescription;

    if(this.state.agree)
      propertyDescription="true"
    else
      propertyDescription="false"


    var data ={
        propertyId:this.props.match.params.propertyId,
        mon:this.state.mon,
        tue:this.state.tue,
        wed:this.state.wed,
        thu:this.state.thu,
        fri:this.state.fri,
        sat:this.state.sat,
        sun:this.state.sun,
        weekdayPrice:this.state.weekdayp,
        weekendPrice:this.state.weekdayp,
        propertyDescription:propertyDescription
    }

    console.log("data",data);
    var header = { "Content-Type": "application/JSON" };
    console.log("Data",data);
    axios.post(API_URL + "/property/availability", data, header).then(response => {
      if (response.status === 201) {
        this.setState({ status: "Success" });
        this.props.history.push(`/hostdashboard/${sessionStorage.userName}`)
      } else {
        //console.log(response);
        alert("Error in creating property");
        this.setState({ status: "Error in creating property" });
      }
    });
};


  RemoveButton = (e) => {
          e.preventDefault();
          var propertyDescription;
        if(this.state.agree)
          propertyDescription="true"
        else
          propertyDescription="false"
        var data ={
          propertyId:this.props.match.params.propertyId,
          propertyDescription:propertyDescription
        }
      var header = { "Content-Type": "application/JSON" };
    //  console.log(data);
      axios.post(API_URL + "/property/delete", data, header).then(response => {
        if (response.status === 201) {
          this.setState({ status: "Success" });
          this.props.history.push(`/hostdashboard/${sessionStorage.userName}`)
        } else {
       //   console.log(response);
          alert("Error in creating property");
          this.setState({ status: "Error in creating property" });
        }
      });
  };
  showReservation = property => {
    this.props.history.push(`/reservation/${this.props.match.params.propertyId}`);
  };


  render() {


    let displayImage = null;           
    if(!this.state.properties.picture)
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
    else if(this.state.properties.picture)
    {
        displayImage = (
            <div>
                <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src = {this.state.properties.picture} height="300" width="420" alt="description"/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
    return (
      <>
      <div id="mainbody">
          {/* {redirectvar} */}
          <div className="container main-content">
              <div class="property_details">
                  {displayImage}
                  <br/>
                      
                  <div class="col-md-7 right-side">
                      <hr></hr>
                      <h3>{this.state.properties.propertyDescription}</h3>
                      <p class="info"> <b> Address : </b> {this.state.properties.streetName}, {this.state.properties.city}, {this.state.properties.zipcode}</p>
                      <p class="info"> <b> Property Type : </b> {this.state.properties.propertyType}</p>
                      <p class="info"> <b> Sharing Type : </b> {this.state.properties.sharingType} </p>
                      <p class="info">  <b>Total Square Footage :</b> {this.state.properties.totalSquareFootage}</p>
                      <p class="info">  <b>Number Of Rooms :</b> {this.state.properties.numberOfRooms}</p>
                      <div style={{width: '30%'}} class="form-group">
                      <b>$</b>   <input 
                            onChange={this.onChange.bind(this)} 
                            defaultValue={this.state.properties.weekdayPrice}
                              type="text" 
                              id="weekdayp"
                              class="form-control" 
                              name="weekdayp" 
                              placeholder="Dollar" 
                            /> <b>per weekday night</b>
                        </div>
                        <div style={{width: '30%'}} class="form-group">
                      <b>$</b>   <input 
                            onChange={this.onChange.bind(this)} 
                            defaultValue={this.state.properties.weekendPrice}
                              type="text" 
                              id="weekendp"
                              class="form-control" 
                              name="weekendp" 
                              placeholder="Dollar" 
                            /> <b>per weekend night</b>
                        </div>

                        <div class="row" >

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
                              id="sun"
                            //  value="true"
                              defaultChecked={this.state.properties.sun}
                              onClick={this.sunCheckboxHandler.bind()}
                            />{" "}
                            Sunday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="mon"
                             //value={this.state.properties.mon}
                             defaultChecked={this.state.properties.mon}
                             onClick={this.monCheckboxHandler.bind()}
                            />{" "}
                            Monday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="tue"
                            //  value="true"
                              defaultChecked={this.state.properties.tue}
                              onClick={this.tueCheckboxHandler.bind()}
                            />{" "}
                            Tuesday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="wed"
                            //  value="true"
                              defaultChecked={this.state.properties.wed}
                              onClick={this.wedCheckboxHandler.bind()}
                            />{" "}
                            Wednesday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="thu"
                             // value="true"
                              defaultChecked={this.state.properties.thu}
                              onClick={this.thuCheckboxHandler.bind()}
                            />{" "}
                            Thursday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="fri"
                             // value="true"
                               defaultChecked={this.state.properties.fri}
                               onClick={this.friCheckboxHandler.bind()}
                            />{" "}
                            Friday
                          </label>
                          <label>
                            <input
                              type="checkbox"
                              name="sat"
                              //value="true"
                              defaultChecked={this.state.properties.sat}
                              onClick={this.satCheckboxHandler.bind()}
                            />{" "}
                            Saturday
                          </label>
                        </fieldset>

                        <label>
                            For Above changes, You need to pay 15% extra to the customer's have booking in next 7 days:   &nbsp; &nbsp;
                            <input
                              type="checkbox"
                              name="sat"
                              // value="true"
                              onClick={this.agreeCheckboxHandler.bind()}
                            />{" "}
                            Agree
                          </label>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
            <br/>
            </div>
              </div>

                      <hr></hr>
                      <button class="btn btn-danger" name="BookButton"  onClick={this.RemoveButton}>
                          <span>Remove Property</span>
                      </button>&nbsp;
                      <button class="btn btn-danger" name="BookButton"  onClick={this.ChangeAvailability}>
                          <span>Change Availability</span>
                      </button>&nbsp;
                      <button class="btn btn-danger" name="BookButton"  onClick={this.showReservation}>
                          <span>Reservation Details</span>
                      </button>
               
          </div>
      </div>
      <br/>
  </>
    );
  }
}

export default PropertyDetails;
