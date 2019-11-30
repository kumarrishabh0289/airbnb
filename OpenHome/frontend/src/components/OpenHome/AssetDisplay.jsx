import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { API_URL } from '../../Constants'
import axios from 'axios';
import Draggable from 'react-draggable';
class AssetDisplay extends Component {

    constructor(props) {
        super(props)

        this.state = {
            welcomeMessage: 'Hey You Are Authorized',
            asset: [],
        }


    }

    componentDidMount() {
        let id = 3;
        axios.get(API_URL + '/asset', { headers: { id :id} })
            .then((response) => {
                console.log(response.data);
                this.setState({
                    asset: this.state.asset.concat(response.data)
                });
            });
    }
    ProgressButton = (asset) => {
        
        this.props.history.push(`/`)
    }

    Sensor = () => {
       
        this.props.history.push(`/`)
    }

    render() {
       
      
        
            return (
                <div class="container">
                    

                    <div class="body-div">
                        <br />
                        <h2>OPenhome Dashboard</h2><br />
                        <h4>Welcome, </h4>
                        <div class="card-columns">
                            {
                                this.state.asset.map(asset => {

                                    return (

                                        <Draggable>
                                            <div>

                                                <div class="card bg-info text-white">
                                                    <div class="card-header">
                                                        {asset.name}
                                                    </div>
                                                    <div class="card-body ">
                                                        <p class="card-text">
                                                        <img src={asset.image} height="200" width="250" alt="description"/>
                                                            <table>
                                                          
                                                                <tr>
                                                                    <th>Asset ID</th><td>{asset.id}</td>
                                                                </tr>
                                                                

                                                                                                                             

                                                            </table>

                                                        </p>
                                                    </div>
                                                    <div class="card-footer">
                                                    <button onClick={() => this.ProgressButton(asset)} class="btn btn-primary">Goto Machine Dashboard</button><br/> <br/> <button onClick={() => this.Sensor()} class="btn btn-danger">Display Machine's Sensor Data</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </Draggable>

                                    )
                                })
                            }


                        </div>
                    </div>

                    <Link to="/"><button class="btn btn-success">Create </button></Link>
                </div>
            )
       
        
    }



}


export default AssetDisplay