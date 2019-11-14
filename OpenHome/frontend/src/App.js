import React, { Component } from 'react';

import OpenHome from './components/OpenHome/OpenHome'
import './App.css';
import './bootstrap.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        {/*<Counter/>*/}
        <OpenHome />
      </div>
    );
  }
}


export default App;