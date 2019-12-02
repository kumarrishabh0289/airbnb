import React, { Component } from "react";
//import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
//import Draggable from "react-draggable";

class PropertyDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      properties: []
    };
  }


  onChange (event) {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

  componentDidMount() {
    console.log(this.props.match.params.propertyId);
    axios
      .get(API_URL + `/property/${this.props.match.params.propertyId}`, {
        headers: { "Content-Type": "application/json" }
      })
      .then(response => {
        console.log(response.data);
        this.setState({
          properties: response.data
        });
        console.log("properties",this.state.properties)
      });
  }


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
                              class="form-control" 
                              name="Doller" 
                              placeholder="Doller" 
                            /> <b>per weekday night</b>
                        </div>
                        <div style={{width: '30%'}} class="form-group">
                      <b>$</b>   <input 
                            onChange={this.onChange.bind(this)} 
                            defaultValue={this.state.properties.weekendPrice}
                              type="text" 
                              class="form-control" 
                              name="Doller" 
                              placeholder="Doller" 
                            /> <b>per weekend night</b>
                        </div>

                        <div class="row" >

<div class="col-sm-6 col-md-6">

    <div class="col-sm-8 col-md-8">
        <label for="where"><h5>Room start Date</h5></label>
        <input onChange = {this.ChangeHandler} type="date" name="startDate" id="startDate" class="form-control js-Date" />
        <i class="icon-calendar form-control-icon" aria-hidden="true">
        </i>
    </div>

</div>

<div class="col-sm-6 col-md-6">

    <div class="col-sm-8 col-md-8">
        <label for="where"><h5>Room end Date</h5></label>
        <input onChange = {this.ChangeHandler} type="date" name="endDate" id="endDate" class="form-control js-Date"/>
    </div>
    
</div>
</div>
<br/>

                      <hr></hr>
                      <button class="btn btn-danger" name="BookButton"  onClick={this.SearchButton} >
                          <span>Previous Page</span>
                      </button>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                      <button class="btn btn-danger" name="BookButton"  >
                          <span>Remove Property</span>
                      </button>
                  </div>
              </div>
          </div>
      </div>
      <br/>
  </>
    );
  }
}

export default PropertyDetails;
