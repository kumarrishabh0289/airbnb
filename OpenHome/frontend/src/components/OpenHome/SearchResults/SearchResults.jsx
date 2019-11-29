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
            endDate: '',
            startDate:'',
            sharingType:'Full',
            propertyType:'Apartment',
            propertyDescription: '',
            wifi : 'true',
            priceRange : '1 to 100',
            information: ''
        }
        this.ChangeHandler = this.ChangeHandler.bind(this);
        this.BookButton = this.BookButton.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangePropertyType = this.handleChangePropertyType.bind(this);
        this.handleChangeWifi = this.handleChangeWifi.bind(this);
        this.handleChangePricerange = this.handleChangePricerange.bind(this);
    }

    componentWillMount() {

    }

    componentDidMount(){

        if(this.props.location.state){
            this.setState({
                information: this.props.location.state.responseData,
            })
        }

    }

    BookButton = (e) => {
        //var headers = new Headers();
        e.preventDefault();
        if(this.state.location === "")
            alert("Address is Empty");
        else if(this.state.startDate === "")
            alert("CHECK-IN Date is Empty");
        else if(this.state.endDate === "")
            alert("CHECKOUT Date is Empty");
        else{
                console.log(new Date());
            const data = {
                city : this.state.location,
                endDate : this.state.endDate,
                startDate : this.state.startDate,
                sharingType : this.state.sharingType,
                propertyType: this.state.propertyType,
                propertyDescription: this.state.propertyDescription,
                wifi : this.state.wifi,
                priceRange : this.state.priceRange
            }
           // alert('Your favorite flavor is: ' + this.state.priceRange);
            console.log(data);
            // axios.defaults.withCredentials = true;
      //      axios.get(`${PROPERTY_URL}/search/property`,data)
            // axios.post(`http://localhost:8181/search/property`,data, data)
            //     .then(response => {
            //         console.log("Status Code : ",response.status);
            //         if(response.status === 200){
            //             // this.setState({
            //             //     flag : true,
            //             //     responseData:response.data,
            //             //     startDate:stringStartDate,
            //             //     endDate:stringEndDate
            //             // })
            //             this.props.history.push(`/search/searchResults`)
            //             console.log(response);
            //             if(!response.data){
            //                 alert("No Available Properties")
            //             }
            //         }
            //         else{
            //             this.setState({
            //                 flag : false
            //             })
            //         }
            //     })
            //     .catch(err =>{
            //         alert(err);
            //     });


        }
    }

    handleChange(event) {
        this.setState({sharingType: event.target.value});
    }

    handleChangePropertyType(event) {
        this.setState({propertyType: event.target.value});
    }

    handleChangeWifi(event) {
        this.setState({wifi: event.target.value});
    }

    handleChangePricerange(event) {
        this.setState({priceRange: event.target.value});
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
                                        {/* <h1>Images Pending</h1> */}
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
                                
                               
                                <hr></hr>
                                <button class="btn btn-danger" name="BookButton">
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
        else
        {
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
            // <div>
            //     <div class="container-fluid">
            //         <br />
            //         <br />
            //         <div class="row" >
            //             <div class="col-sm-1 col-md-1"></div>

            //             <div class="col-sm-5 col-md-5" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=50)", borderRadius: '10px' }}>

            //                 <h1>
            //                     Book homes, hotels, and more on Airbnb
            //                     </h1>

            //                 <form >
            //                     <div class="row" >

            //                         <div class="col-sm-12 col-md-12">
            //                             <br />
            //                             <div class="form-group required">
            //                                 <label for="where"><h5>WHERE</h5></label>
            //                                 <input type="text" onChange = {this.ChangeHandler} class="form-control" placeholder="Anywhere" name="location" id="location"/>

            //                             </div>

            //                         </div>

            //                     </div>

            //                     <div class="row" >

            //                         <div class="col-sm-12 col-md-12">
            //                             <div class="form-group">
            //                                 <br/>
            //                                 <input type="submit" onClick={this.SearchButton} class="form-control btn btn-danger" />
            //                                 <br/>
            //                                 <br/>
            //                             </div>
            //                         </div>
                                    
            //                     </div>
            //                 </form>
            //             </div>

            //             </div>


            //         </div>
            //     </div>
            <div>
            {/* <div class="property_detials">
                <div class="row">
                    <div class="col-md-5">

                    </div>
                    <div class="col-md-7 right-side">
                        <h3></h3><br></br>
                        <p class="info"></p>
                        <p class="info"> Bedrooms : , Bathrooms , Accomodates </p>
                        <p class="price">per night</p>
                        
                        <h4><b>Reviews</b></h4><br></br>
                        <p class="info"></p>
                        <hr></hr>
                        <button class="btn btn-primary book-button"  name="BookButton">
                            <span>Book</span>
                        </button>
                    </div>
                </div>
            </div>
            <hr></hr> */}

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