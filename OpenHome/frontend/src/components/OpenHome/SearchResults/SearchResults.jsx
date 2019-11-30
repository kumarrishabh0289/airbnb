import React, { Component } from 'react'
//import { Link } from 'react-router-dom'
//import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap/dist/js/bootstrap.js';
import 'bootstrap/dist/js/bootstrap.min.js';


class SearchResults extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '',
            end_date: '',
            start_date: '',
            sharingType: 'Full',
            propertyType: 'Apartment',
            propertyDescription: '',
            wifi: 'true',
            priceRange: '1 to 100',
            information: '',
            booking_total_room: 0
        }
        this.ChangeHandler = this.ChangeHandler.bind(this);
        this.BookButton = this.BookButton.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangePropertyType = this.handleChangePropertyType.bind(this);
        this.handleChangeWifi = this.handleChangeWifi.bind(this);
        this.handleChangePricerange = this.handleChangePricerange.bind(this);
        this.handleme = this.handleme.bind(this);
    }

    componentWillMount() {

    }

    componentDidMount() {

        if (this.props.location.state) {
            this.setState({
                information: this.props.location.state.responseData,
                start_date: this.props.location.state.startDate,
                end_date: this.props.location.state.endDate,

            })
        }
        console.log("print location", this.props.location.state);

    }

    BookButton = (property) => {
       
       
        sessionStorage.setItem('startDate',this.state.start_date)
        sessionStorage.setItem('endDate',this.state.end_date)
        sessionStorage.setItem('propertyId',property.propertyId)
        sessionStorage.setItem('booking_total_room',this.state.booking_total_room)
        this.props.history.push(`/bookingconfirmation`)
    }

    handleme(event) {
        this.setState({

            [event.target.name]: event.target.value
        });
    }

    handleChange(event) {
        this.setState({ sharingType: event.target.value });
    }

    handleChangePropertyType(event) {
        this.setState({ propertyType: event.target.value });
    }

    handleChangeWifi(event) {
        this.setState({ wifi: event.target.value });
    }

    handleChangePricerange(event) {
        this.setState({ priceRange: event.target.value });
    }

    ChangeHandler(e) {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }

    render() {

        let displayImage = null;
        //  let reviews = null;
        let view = null;
        if (this.state.information.length > 0) {
            view = this.state.information.map(property => {

                if (!property.picture) {
                    console.log("data", property.city)
                    displayImage = (
                        <div>

                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        {/* <h1>Images Pending</h1> */}
                                        <img src="https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx" height="300" width="420" alt="description" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    )
                }
                else if (property.picture) {
                    displayImage = (
                        <div>

                            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src={property.picture} height="300" width="420" alt="description" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    )
                }

                return (
                    <div>
                        <div class="property_details">
                            <div class="row">
                                <div class="col-md-5">
                                    {displayImage}
                                </div>
                                <div class="col-md-7 right-side">
                                    <hr></hr>
                                    <h3>{property.propertyDescription}</h3><br></br>
                                    <p class="info">Address : {property.streetName}, {property.city}, {property.zipcode}</p>
                                    <p class="info"> Property Type : {property.propertyType}, Sharing Type : {property.sharingType}, Total Square Footage : {property.totalSquareFootage}, Number Of Rooms : {property.numberOfRooms}</p>
                                    <p class="price">$ {property.Tariff} per night</p>
                                    <p>Number of Room you Want: </p>
                                    <input type="text" name="booking_total_room" onChange={this.handleme} />


                                    <hr></hr>
                                    <button class="btn btn-danger" name="BookButton" onClick={() => this.BookButton(property)}>
                                        <span>Book</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <hr></hr>
                    </div>
                )
            })
        }
        else {
            view = (
                <div class="property_detials">
                    <h3><b>No results !!</b></h3>
                    <h3>To get more results, try adjusting your search by changing your dates.</h3>
                </div>
            );
        }
        console.log("CHECK THE DATA");
        console.log(this.state.information);
        return (

            <div>


                <div id="mainbody">
                    <div class="container main-content">
                        <br></br>
                        {view}
                    </div>
                </div>

            </div>
        )
    }
}

export default SearchResults