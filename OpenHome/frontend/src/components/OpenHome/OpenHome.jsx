import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AuthenticatedRoute from "./AuthenticatedRoute";
import LoginComponent from "./LoginComponent";
import ErrorComponent from "./ErrorComponent";
import HeaderComponent from "./HeaderComponent";
//import FooterComponent from "./FooterComponent";
import LogoutComponent from "./LogoutComponent";
import WelcomeComponent from "./WelcomeComponent";
import SearchResults from "./SearchResults/SearchResults";
import SearchResultDetails from "./SearchResults/SearchResultDetails.jsx";
import FrontPage from "./FrontPage";
import HostDashboard from "../OwnerProperty/HostDashboard";
import PropertyDetails from "../OwnerProperty/PropertyDetails";
import CreateProperty from "../OwnerProperty/CreateProperty";
import SignUP from "./SignUp";
import BookingConfirmation from "./BookingConfirmation/BookingConfirmation"
import WelcomeUser from "./WelcomeUser";
import PropertyReservationDetails from "./PropertyReservationDetails";
import UserBilling from "./UserBilling";
import HostBilling from "./HostBilling";


// import AssetDisplay from "./AssetDisplay.jsx";

class OpenHome extends Component {
  render() {
    return (
      <div className="OpenHomeApp">
        <Router>
          <>
            <HeaderComponent />
            <Switch>
              <Route path="/" exact component={FrontPage} />
              <Route path="/search/searchResults" component={SearchResults} />
              <Route path="/search/searchResult/:propertyId" component={SearchResultDetails} />
              <Route path="/login" component={LoginComponent} />
              <AuthenticatedRoute path="/bookingconfirmation" component={BookingConfirmation} />
              <AuthenticatedRoute path="/welcome" component={WelcomeComponent} />
              <Route path="/hostdashboard/:name" component={HostDashboard} />
              <Route path="/signup" component={SignUP} />
              <Route path="/welcomeuser/:name" component={WelcomeUser} />
            
              <Route path="/property/new" component={CreateProperty} />
              <Route path="/userbilling" component={UserBilling} />
              <Route path="/hostbilling" component={HostBilling} />
              <Route path="/property/:propertyId" component={PropertyDetails} />
              <Route path="/reservation/:propertyId" component={PropertyReservationDetails} />
              <AuthenticatedRoute path="/logout" component={LogoutComponent} />

              <Route component={ErrorComponent} />
            </Switch>
           
          </>
        </Router>
      </div>
    );
  }
}

export default OpenHome;
