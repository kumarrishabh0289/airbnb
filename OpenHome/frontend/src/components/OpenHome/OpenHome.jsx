import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AuthenticatedRoute from "./AuthenticatedRoute.jsx";
import LoginComponent from "./LoginComponent.jsx";
import ErrorComponent from "./ErrorComponent.jsx";
import HeaderComponent from "./HeaderComponent.jsx";
import FooterComponent from "./FooterComponent.jsx";
import LogoutComponent from "./LogoutComponent.jsx";
import WelcomeComponent from "./WelcomeComponent.jsx";
import SearchResults from "./SearchResults/SearchResults";
import SearchResultDetails from "./SearchResults/SearchResultDetails";
import FrontPage from "./FrontPage.jsx";
import PropertiesDashboard from "../OwnerProperty/PropertiesDashboard.jsx";
import PropertyDetails from "../OwnerProperty/PropertyDetails.jsx";
import CreateProperty from "../OwnerProperty/CreateProperty.jsx";
import SignUP from "./SignUp";
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
              <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent} />
              <Route path="/dashboard" component={PropertiesDashboard} />
              <Route path="/signup" component={SignUP} />
              <Route path="/property/new" component={CreateProperty} />
              <Route path="/property/:propertyId" component={PropertyDetails} />
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
