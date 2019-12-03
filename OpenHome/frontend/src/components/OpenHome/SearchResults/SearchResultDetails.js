// import React, { Component } from "react";
// //import { Link } from 'react-router-dom'
// //import axios from 'axios';

// class SearchResults extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       location: "",
//       endDate: "",
//       startDate: "",
//       sharingType: "Full",
//       propertyType: "Apartment",
//       propertyDescription: "",
//       wifi: "true",
//       priceRange: "1 to 100",
//       information: "",
//       datatest: false
//     };
//     this.ChangeHandler = this.ChangeHandler.bind(this);
//     this.handleChange = this.handleChange.bind(this);
//   }

//   componentWillMount() {}

//   componentDidMount() {
//     if (this.props.location.state) {
//       this.setState({
//         information: this.props.location.state.responseData1
//       });
//     }
//   }

//   OpenProperty = property => {
//     this.setState({
//       datatest: true
//     });
//     this.props.history.push(`/search/searchResult/${property.propertyId}`);
//   };

//   handleChange(event) {
//     this.setState({ sharingType: event.target.value });
//   }

//   ChangeHandler(e) {
//     let change = {};
//     change[e.target.name] = e.target.value;
//     this.setState(change);
//   }

//   render() {

//     let displayImage = null;
//     let view = null;
//     if (this.state.information.length > 0) {
//       view = this.state.information.map(property => {
//         if (!property.picture) {
//           console.log("data", property.city);
//           displayImage = (
//             <div>
//               <div
//                 id="carouselExampleControls"
//                 class="carousel slide"
//                 data-ride="carousel"
//               >
//                 <div class="carousel-inner">
//                   <div class="carousel-item active">
//                     <img
//                       src="https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx"
//                       height="300"
//                       width="420"
//                       alt="description"
//                     />
//                   </div>
//                 </div>
//               </div>
//             </div>
//           );
//         } else if (property.picture) {
//           displayImage = (
//             <div>
//               <div
//                 id="carouselExampleControls"
//                 class="carousel slide"
//                 data-ride="carousel"
//               >
//                 <div class="carousel-inner">
//                   <div class="carousel-item active">
//                     <img
//                       src={property.picture}
//                       height="300"
//                       width="420"
//                       alt="description"
//                     />
//                   </div>
//                 </div>
//               </div>
//             </div>
//           );
//         }

//         return (
//           <div className="container">
//             <div class="property_details">
//               <div class="row">
//                 <div class="col-md-5">{displayImage}</div>
//                 <div class="col-md-7 right-side">
//                   <hr></hr>
//                   <h3>{property.propertyDescription}</h3>
//                   <br></br>
//                   <p class="info">
//                     Address : {property.streetName}, {property.city},{" "}
//                     {property.zipcode}
//                   </p>
//                   <p class="info">
//                     {" "}
//                     Property Type : {property.propertyType}, Sharing Type :{" "}
//                     {property.sharingType}, Total Square Footage :{" "}
//                     {property.totalSquareFootage}, Number Of Rooms :{" "}
//                     {property.numberOfRooms}
//                   </p>
//                   {/* <p class="price">$ {property.Tariff} per night</p> */}

//                   <hr></hr>
//                   <button
//                     class="btn btn-danger"
//                     name="BookButton"
//                     onClick={() => this.OpenProperty(property)}
//                   >
//                     <span>Property Details</span>
//                   </button>
//                 </div>
//               </div>
//             </div>
//             <hr></hr>
//           </div>
//         );
//       });
//     } else {
//       view = (
//         <div class="property_detials">
//           <h3>
//             <b>No results !!</b>
//           </h3>
//           <h3>
//             To get more results, try adjusting your search by changing your
//             dates.
//           </h3>
//         </div>
//       );
//     }
//     // console.log("CHECK THE DATA");
//     // console.log(this.state.information);
//     return (
//       <div>
//         {/* {redirectvar} */}
//         <div id="mainbody">
//           <div class="container main-content">
//             <br></br>
//             {view}
//           </div>
//         </div>
//       </div>
//     );
//   }
// }

// export default SearchResults;
import React, { Component } from 'react'
//import { Link } from 'react-router-dom'
import axios from 'axios';
import { Redirect } from 'react-router';
// import { da } from 'date-fns/esm/locale';
// import moment from 'moment';
// import {DatetimePickerTrigger} from 'rc-datetime-picker';
// import {DatetimePicker} from 'rc-datetime-picker';


class SearchResultDetails extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            welcomeMessage: 'Hey You Are Authorized',
            responseData:'',
            responseData1:'',
            bookingPrice:''
       //     moment: moment()
        }    
        
        this.SearchButton = this.SearchButton.bind(this);
        this.BookButton = this.BookButton.bind(this);
    }

    componentDidMount(){

         //   console.log("final",finalp);


        axios.get(`http://localhost:8181/property/${this.props.match.params.propertyId}`)
        .then(response => {
            console.log("Status Code : ",response.status);
            if(response.status === 200){
                this.setState({
                    responseData:response.data
                })

                var data = JSON.parse(localStorage.getItem('product_details'));
                var startdate = data.startDate;
                var enddate = data.endDate;
                var myDate = new Date(startdate);
                var myDate1 = new Date(enddate);
                //apply states and do the calculations
        
                   
                    var loop = new Date(myDate);
                    var newDate1 = loop.setDate(loop.getDate() + 1);
                    loop = new Date(newDate1);
                    var newDate2 = myDate1.setDate(myDate1.getDate() + 1);
                    myDate1 = new Date(newDate2);
                    var weekdayCnt = 0,weekendCnt = 0;
                    while(loop <= myDate1){
                        var newDate = loop.setDate(loop.getDate() + 1);
                        loop = new Date(newDate);
                        
                        if(loop.getDay() === 6 || loop.getDay() === 0)
                            weekdayCnt++;
                        else
                            weekendCnt++;
                    }
                    // console.log(weekdayCnt)
                    // console.log(weekendCnt)
                    var finalp = 0;
                    finalp = weekdayCnt * this.state.responseData.weekdayPrice;
                    finalp = finalp + weekendCnt * this.state.responseData.weekendPrice;
                    this.setState({
                        bookingPrice:finalp
                    })
                    // console.log(finalp);
                    // console.log("ress",this.state.responseData.weekdayPrice);
                    // console.log("ress",this.state.responseData.weekendPrice);
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

    SearchButton = (e) => {
        var data = JSON.parse(localStorage.getItem('product_details'));
        axios.post(`http://localhost:8181/search/property`,data)
        .then(response => {
            console.log("Status Code : ",response.status);
            if(response.status === 200){
                this.setState({
                    responseData1:response.data//,
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


    BookButton = (e) => {
       var data = JSON.parse(localStorage.getItem('product_details'));
            console.log(data)
        const payload = {
            booked_price : 50,
            booked_price_weekend : 10,
            booked_price_weekday : 11,
            booking_date: data.startDate,
            startDate: data.startDate,
            endDate: data.endDate,
            guestId: 1,
            propertyId:1
        }
        console.log("payload",payload);
        axios.post(`http://localhost:8181/reservation/new`,payload)
        .then(response => {
            console.log("Status Code : ",response.status);
            if(response.status === 200){
                this.setState({
                    responseData1:response.data
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


    // handleChange = (moment) => {
    //     this.setState({
    //       moment
    //     });
    //   }


    render() {
        let redirectvar = null
        if(this.state.responseData1){
            console.log("should redirect")
            console.log(this.state.responseData1);
            redirectvar = <Redirect to= {{
                pathname: '/search/searchResults',
                state:{
                    responseData1: this.state.responseData1
                }
            }}/>
        }

        // const shortcuts = {
        //     'Today': moment(),
        //     'Yesterday': moment().subtract(1, 'days'),
        //     'Clear': ''
        //   };


        let displayImage = null;           
        if(!this.state.responseData.picture)
        {
            displayImage = (
                <div>
                    <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src = "https://www.rejournals.com/getattachment/d25d399a-5f87-4e6d-96a0-392fc34f745c/file.aspx" height="300" width="420" alt="description"/>
                            </div>
                        </div>
                     </div>
                </div>
            )
        }
        else if(this.state.responseData.picture)
        {
            displayImage = (
                <div>
                    <div id="carouselExampleControls" class="carousel slide right-side" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src = {this.state.responseData.picture} height="300" width="420" alt="description"/>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }



        return (
            <>
                <div id="mainbody">
                    {redirectvar}


                    {/* <DatetimePicker
                        moment={this.state.moment}
                        onChange={this.handleChange}
                    /> */}

                    <div className="container main-content">
                        <div class="property_details">
                            {displayImage}
                            <div class="col-md-7 right-side">
                                <hr></hr>
                                <h3>{this.state.responseData.propertyDescription}</h3>
                                <p class="info"> <b> Address : </b> {this.state.responseData.streetName}, {this.state.responseData.city}, {this.state.responseData.zipcode}</p>
                                <p class="info"> <b> Property Type : </b> {this.state.responseData.propertyType}</p>
                                <p class="info"> <b> Sharing Type : </b> {this.state.responseData.sharingType} </p>
                                <p class="info">  <b>Total Square Footage :</b> {this.state.responseData.totalSquareFootage}</p>
                                <p class="info">  <b>Number Of Rooms :</b> {this.state.responseData.numberOfRooms}</p>
                                <p class="price">$ {this.state.bookingPrice} per night</p>
                                <hr></hr>
                                <button class="btn btn-danger" name="BookButton"  onClick={this.SearchButton} >
                                    <span>Previous Page</span>
                                </button>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="btn btn-danger" name="BookButton" onClick={this.BookButton} >
                                    <span>Book Property</span>
                                </button>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="btn btn-danger" name="BookButton" onClick={this.BookButton} >
                                    <span>CheckIN</span>
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
                <br/>
            </>
        )
    }



}


export default SearchResultDetails
