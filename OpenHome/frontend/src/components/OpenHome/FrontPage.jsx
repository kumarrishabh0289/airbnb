import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationForApiService from './AuthenticationForApiService.js'

class FrontPage extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    componentWillMount() {

    }

    render() {
        return (
            <div>
                <div class="container-fluid">
                    <br />
                    <br />
                    <div class="row" >
                        <div class="col-sm-1 col-md-1"></div>

                        <div class="col-sm-5 col-md-5" style={{ backgroundColor: "white", opacity: 1, filter: "Alpha(opacity=50)", borderRadius: '10px' }}>

                            <h1>
                                Book homes, hotels, and more on Airbnb
                                </h1>

                            <form>
                                <div class="row" >

                                    <div class="col-sm-12 col-md-12">
                                        <br />
                                        <div class="form-group">
                                            <label for="where"><h5>WHERE</h5></label>
                                            <input type="text" class="form-control" id="where" placeholder="Anywhere" />

                                        </div>

                                    </div>
                                    <div class="col-sm-1 col-md-1">

                                    </div>

                                </div>
                                <div class="row" >

                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>CHECK-OUT</h5></label>
                                            <input type="text" class="form-control" id="where" placeholder="Check-Out" />

                                        </div>

                                    </div>
                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>CHECK-IN</h5></label>
                                            <input type="text" class="form-control" id="where" placeholder="Check-In" />

                                        </div>
                                    </div>

                                </div>

                                <div class="row" >

                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>ADULTS</h5></label>
                                            <div class="form-group">

                                                <select class="form-control" id="sel1">
                                                    <option>0</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                </select>
                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-sm-6 col-md-6">

                                        <div class="form-group">
                                            <label for="where"><h5>CHILDREN</h5></label>
                                            <div class="form-group">

                                                <select class="form-control" id="sel1">
                                                    <option>0</option>
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <div class="row" >

                                    <div class="col-sm-12 col-md-12">
                                        <div class="form-group">
                                        
                                            <br/>
                                            <input type="submit" class="form-control btn btn-danger" />
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