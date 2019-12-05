import React, { Component } from "react";
// import { Link } from "react-router-dom";
import { API_URL } from "../../Constants";
import axios from "axios";
class UserBilling extends Component {
  constructor(props) {
    super(props);

    this.state = {
      billing: [],
     
      hasFailed: false,
      month: "",
      year: ""
    };
  }

  componentDidMount() {
   
  }

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

  

  billing = (e) => {
    console.log("billing called")
    e.preventDefault();
    var data = {
      "guestId":sessionStorage.userId,
      "month":this.state.month,
      "year":this.state.year,

    };

    axios.post(API_URL + `/reservation/billing/guest`, data)
      .then(response => {
        console.log("response",response)
        this.setState({
          billing: response.data
        })
       
      })
      .catch(err => {
        
        this.setState({
          hasFailed: true,
          
        })
      })

  }

  render() {
    if (this.state.billing.length === 0){
      var msg = " No Details"
     
    }
    var total_paid = 0
    
    return (
      <>
      <br/>
        
        <div class="container" style={{ backgroundColor: "yellow", opacity: .9, filter: "Alpha(opacity=90)", borderRadius: '10px' }}>
          <br />
          <h1>Billing</h1>
          <div className="container">
          
            <div class="row" >
            
              <div class="col-sm-5 col-md-5">

               
                  <label for="where"><h5>Month</h5></label>
                  <div class="form-group">
                  

                    <select class="form-control" value={this.state.month} onChange={this.handleChange} name="month" >
                      <option value="">Select</option>
                      <option value="January">January</option>
                      <option value="February">February</option>
                      <option value="March">March</option>
                      <option value="April">April</option>
                      <option value="May">May</option>
                      <option value="June">June</option>
                      <option value="July">July</option>
                      <option value="August">August</option>
                      <option value="September">September</option>
                      <option value="October">October</option>
                      <option value="November">November</option>
                      <option value="December">December</option>

                    </select>
                 
                  </div>
                 
                </div>
              

              <div class="col-sm-5 col-md5">

                <div class="form-group">
                  <label for="where"><h5>Year</h5></label>
                  <div class="form-group">

                    <select class="form-control" value={this.state.year} onChange={this.handleChange} name="year" >
                      <option value="">Select</option>
                      <option value="2018">2018</option>
                      <option value="2019">2019</option>
                      <option value="2020">2020</option>
                      <option value="2021">2021</option>
                     

                    </select>

                  </div>

                </div>
               
              </div>
              <div class="col-sm-2 col-md-2">
              <div class="form-group">
                  <label for="where"></label>
                
                  <input type="submit" onClick={this.billing} class="form-control btn btn-danger" />
                </div>
                </div>
              </div>
             
            
            </div>
            {this.state.hasFailed && <div className="alert alert-warning">Data Not Available.</div>}
     
          </div>
      
          <hr /><hr />
        <div class="container" style={{ backgroundColor: "yellow", opacity: .9, filter: "Alpha(opacity=90)", borderRadius: '10px' }}>
         <h3>Billing Details</h3>
        
            <table className="table">
              <tr>
                <th>Booking ID</th>
                <th>Address</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Check-IN</th>
                <th>Check-Out</th>
                <th>Penalty Reason</th>
                <th>Penalty Amount</th>
                
                <th>Total Payable</th>
                <th>Status</th>

                <th></th>
                <th></th>
                <th></th>

              </tr>
              {msg}
              {this.state.billing.map(booking => {
                total_paid = total_paid + parseInt(booking.paymentAmount)
              
                  return (
                    <tr>
                      <td>{booking.id}</td>
                      <td>{booking.address}</td>
                      <td>{booking.description}</td>
                      <td>{booking.startDate}</td>
                      <td>{booking.endDate}</td>
                      <td>{booking.checkInDate}</td>
                      <td>{booking.checkOutDate}</td>
                      <td>{booking.penaltyReason}</td>
                      <td>{booking.penaltyValue}</td>
                    
                      <td>{booking.paymentAmount}</td>

                      <td>{booking.state}</td>
                     
                    </tr>
                  )
               
              })}
            </table>
            <h3>Total Paid Amount in {this.state.month}  {this.state.year} : ${total_paid}</h3>
        </div>

      </>
    )
  }
}

export default UserBilling;
