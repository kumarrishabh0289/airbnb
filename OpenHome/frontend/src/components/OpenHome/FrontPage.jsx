import React, { Component } from 'react'
import { Redirect } from 'react-router';
//import { Link } from 'react-router-dom'
import axios from 'axios';
import { API_URL } from "../../Constants";


class FrontPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            location: '',
            endDate: '',
            startDate:'',
            sharingType:'',
            propertyType:'',
            propertyDescription: '',
            wifi : '',
            priceRange : '',
            responseData: '',
            currentdate:'',
            datefound:false
        }
        this.ChangeHandler = this.ChangeHandler.bind(this);
        this.SearchButton = this.SearchButton.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangePropertyType = this.handleChangePropertyType.bind(this);
        this.handleChangeWifi = this.handleChangeWifi.bind(this);
        this.handleChangePricerange = this.handleChangePricerange.bind(this);
    }

    componentWillMount() {

    }

    SearchButton = (e) => {
        e.preventDefault();

        var date1 = new Date(this.state.startDate);
        var date2 = new Date(this.state.endDate);
        var difference = date2- date1;
        var days = difference/(24*3600*1000);

        // now get the current date    from backend

        var utcstartDate = new Date(date1.getTime() + date1.getTimezoneOffset() * 60000);
        var utcendDate = new Date(date1.getTime() + date1.getTimezoneOffset() * 60000);

        //below is current date from backend
        // console.log("this.state.currentdate",this.state.currentdate)
        var date3 = new Date((this.state.currentdate).slice(0, 10));
        var utcCurrentDate = new Date(date3.getTime() + date3.getTimezoneOffset() * 60000);

        console.log("selected date",utcstartDate)
        console.log("current date from backend",utcCurrentDate)
        var difference1 = utcstartDate - utcCurrentDate;
        var days1 = difference1/(24*3600*1000);

        var difference2 = utcendDate - utcCurrentDate;
        var days2 = difference2/(24*3600*1000);

        console.log("Days1 data",days1);

        if(this.state.location === "")
            alert("Address is Empty");
        else if(this.state.startDate === "")
            alert("CHECK-IN Date is Empty");
        else if(this.state.endDate === "")
            alert("CHECKOUT Date is Empty");
        else if(days1 > 365 || days2 > 365)
            alert("You cannot book for next Year. Please try adjusting your search by changing your dates.");
        else if(days <= 0)
            alert("CHECKOUT date cannot be same or less than Check-IN date. Please try adjusting your search by changing your dates.");
        else if(days1 < 0)
            alert("CheckIN Date cannot be less that Today's date. Please try adjusting your search by changing your dates.");
        else if(days > 14)
            alert("You can't book for more than 14 days. Please try adjusting your search by changing your dates.");
        else{

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
        console.log("data",data);
            localStorage.setItem('product_details', JSON.stringify(data));
           
            axios.post( API_URL + `/search/property`,data)
                .then(response => {
                    console.log("Status Code : ",response.status);
                    if(response.status === 200){
                        this.setState({
                            responseData:response.data//,
                        })
                        console.log(response);
                        if(!response.data){
                            alert("No Available Properties")
                        }
                    }
                    else{
                        this.setState({
                            flag : false
                        })
                    }
                })
                .catch(err =>{
                    alert(err);
                });
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

    componentDidMount(){

        axios.get(API_URL + `/admin/date/`)
        .then(response => {
            console.log("Status Code : ", response.status);
            if (response.status === 200) {
                this.setState({
                    currentdate: response.data,
                    datefound: true
                })
                console.log("response data",response.data)

                if (!response.data) {
                    alert("No Available Properties")
                }
            }
            else {
                this.setState({
                    flag: false
                })
            }
        })
        .catch(err => {
            alert(err);
        });
    }
    

    render() {

        let redirectvar = null
        if(this.state.responseData){
            redirectvar = <Redirect to= {{
                pathname: '/search/searchResults',
                state:{
                    responseData1: this.state.responseData
                }
            }}/>
        }

        return (
            <div>
                {redirectvar}
                <div class="container-fluid">
                    <br />
                    <br />
                    <div class="row" >
                        <div class="col-sm-1 col-md-1"></div>

                        <div class="col-sm-5 col-md-5" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=50)", borderRadius: '10px' }}>
                            <br/>
                            <h2>
                                Book homes, hotels, and more on <mark class="red">OpenHome</mark>
                                </h2>

                            <form >
                                <div class="row" >

                                    <div class="col-sm-12 col-md-12">
                                        <br />
                                        <div class="form-group">
                                            <label for="where"><h5>WHERE *</h5></label>
                                            <input type="text" onChange = {this.ChangeHandler} class="form-control" placeholder="Anywhere" name="location" id="location"/>

                                        </div>

                                    </div>

                                </div>
                                <div class="row" >

                                    <div class="col-sm-6 col-md-6">

                                        <div class="col-sm-8 col-md-8">
                                            <label for="where"><h5>CHECK-IN *</h5></label>
                                            <input onChange = {this.ChangeHandler} type="date" name="startDate" id="startDate" class="form-control js-Date" />
                                            <i class="icon-calendar form-control-icon" aria-hidden="true">
                                            </i>
                                        </div>

                                    </div>
                                    
                                    <div class="col-sm-6 col-md-6">

                                        <div class="col-sm-8 col-md-8">
                                            <label for="where"><h5>CHECKOUT *</h5></label>
                                            <input onChange = {this.ChangeHandler} type="date" name="endDate" id="endDate" class="form-control js-Date"/>
                                        </div>
                                        
                                    </div>
                                </div>
                                <br/>

                                 <div class="row" >

                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>Sharing Type</h5></label>
                                            <div class="form-group">

                                                <select class="form-control" value={this.state.value} onChange = {this.handleChange} >
                                                    <option value="">Select</option>
                                                    <option value="Full">Full</option>
                                                    <option value="Private">Private</option>
                                                </select>
                                                
                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>Property Type</h5></label>
                                            <div class="form-group">

                                                <select class="form-control" value={this.state.value} onChange = {this.handleChangePropertyType}>
                                                    <option value="">Select</option>
                                                    <option value="Apartment">Apartment</option>
                                                    <option value="Condo">Condo</option>
                                                    <option value="Bed and Breakfast">Bed and Breakfast</option>
                                                    <option value="Hostel">Hostel</option>
                                                    <option value="House">House</option>
                                                    <option value="TownHouse">TownHouse</option>
                                                    <option value="Villa">Villa</option>
                                                    <option value="Farmhouse">Farmhouse</option>
                                                    <option value="Cottage">Cottage</option>
                                                    <option value="PentHouse">PentHouse</option>
                                                </select>

                                            </div>

                                        </div>
                                    </div>

                                </div>


                                <div class="row" >
                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                         <label for="where"><h5>Price Range</h5></label>
                                            <div class="form-group">
                                                <select class="form-control" value={this.state.value} onChange = {this.handleChangePricerange}>
                                                    <option value="">Select</option>
                                                    <option value="1 to 100">1 to 100</option>
                                                    <option value="101 to 1000">101 to 500</option>
                                                    <option value="501 to 1000">501 to 1000</option>
                                                    <option value="1001 to 1500">1001 to 1500</option>
                                                    <option value="1501 to 2000">1501 to 2000</option>
                                                    <option value="1001 to 1500">2001 to 2500</option>
                                                    <option value="1501 to 2000">2501 to 3000</option>

                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                         <label for="where"><h5>Wifi Required</h5></label>
                                            <div class="form-group">
                                                <select class="form-control" value={this.state.value} onChange = {this.handleChangeWifi}>
                                                    <option value="">Select</option>
                                                    <option value="Yes">Yes</option>
                                                    <option value="No">No</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row" >
                                    <div class="col-sm-12 col-md-12">
                                        
                                        <div class="form-group">
                                            <label for="where"><h5>Description</h5></label>
                                            <textarea type="noter-text-area" onChange = {this.ChangeHandler} class="form-control" placeholder="Describe the Place Here!" name="propertyDescription" id="propertyDescription" >
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" >

                                    <div class="col-sm-12 col-md-12">
                                        <div class="form-group">
                                            <br/>
                                            <input type="submit" onClick={this.SearchButton} class="form-control btn btn-danger" />
                                            <br/>
                                            <br/>
                                        </div>
                                    </div>
                                    
                                </div>
                            </form>
                        </div>

                        </div>


                    </div>
                </div>
                )
            }
        }
        
export default FrontPage