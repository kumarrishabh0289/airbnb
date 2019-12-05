import React, { Component } from 'react'
import { Link } from 'react-router-dom'


class WelcomeComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            
        }
        
        
    }
    componentDidMount(){
         if(sessionStorage.userRole === "user"){
            this.props.history.push(`/welcomeuser/${sessionStorage.userName}`)
         }
         if(sessionStorage.userRole === "host"){
            this.props.history.push(`/hostdashboard/${sessionStorage.userName}`)
         }
        
    }


    render() {
        if(sessionStorage.userRole === "user"){
        return (
            <>
            <br/>
            
            <div class="col-sm-5 col-md-5 container" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=100)", borderRadius: '10px' }}>
                   <br/>
                <h1>Welcome!</h1>
                <div className="container">
                  <h4>Important Links</h4>
                  <h4><Link className="nav-link" to="/hostdashboard/User"  >User Dash Board</Link></h4>
                   
                </div>
                
                

            </div>
           
           </>
        )
    }
    if(sessionStorage.userRole === "host"){
        return (
            <>
            <br/>
            <div class="col-sm-5 col-md-5 container" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=100)", borderRadius: '10px' }}>
                   <br/>
                <h1>Welcome!</h1>
                <div className="container">
                <h4><Link className="nav-link" to="/welcomeuser/User" >Host Dash Board</Link></h4>
                   
                </div>
                
            </div>
           
          </> 
        )
    }
    else{
        return (
            
                <>
                <h1>Welcome!</h1>
                <div className="container">
                  
                   
                </div>
                <div className="container">
                    
                       
                </div>
                <div className="container">
                   

                </div>

            </>
           
           
        )
    }
    }



}


export default WelcomeComponent