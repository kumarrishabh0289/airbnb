import React, { Component } from 'react'
//import { Link } from 'react-router-dom'
//import axios from 'axios';



class SearchResults extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '',
            endDate: '',
            startDate:'',
            sharingType:'Full',
            propertyType:'Apartment',
            propertyDescription: '',
            wifi : 'true',
            priceRange : '1 to 100',
            information: '',
            datatest:false
        }
        this.ChangeHandler = this.ChangeHandler.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentWillMount() {

    }

    componentDidMount(){

        if(this.props.location.state){
            this.setState({
                information: this.props.location.state.responseData1,
            })
        }
    }


    OpenProperty = property => {
        this.setState({
            datatest: true,
        })
        this.props.history.push(`/search/searchResult/${property.propertyId}`);
      };

    handleChange(event) {
        this.setState({sharingType: event.target.value});
    }


    ChangeHandler(e) {
        let change = {}
        change[e.target.name] = e.target.value
        this.setState(change)
    }

    render() {

        let displayImage = null;
        let view = null;
        if(this.state.information.length>0)
        {
            view = this.state.information.map(property => {    
                    if(!property.picture)
                    {
                        console.log("data",property.city)
                        displayImage = (
                            <div>
                                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img src = "https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx" height="300" width="420" alt="description"/>
                                        </div>
                                    </div>
                                 </div>
                            </div>
                        )
                    }
                    else if(property.picture)
                    {
                        displayImage = (
                            <div>
                                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img src = {property.picture} height="300" width="420" alt="description"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )
                    }

                return(
                    <div className="container">
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
                                {/* <p class="price">$ {property.Tariff} per night</p> */}

                                <hr></hr>
                                <button class="btn btn-danger" name="BookButton"  onClick={() => this.OpenProperty(property)}>
                                    <span>Property Details</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr></hr>
                    </div>
                )
            })
        }
        else
        {
            view = (
                    <div class="property_detials">
                        <h3><b>No results !!</b></h3>
                         <h3>To get more results, try adjusting your search by changing your dates.</h3>
                    </div>
                );
        }
        // console.log("CHECK THE DATA");
        // console.log(this.state.information);
        return (
            
            <div>
                   {/* {redirectvar} */}
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