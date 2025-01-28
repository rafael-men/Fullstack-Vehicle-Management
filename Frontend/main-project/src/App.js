import React from 'react';
import {  BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import Navbar from './Components/Navbar';
import NewVehicle from './Pages/NewVehicle'


function App() {
  return (
    <div className="App">
      <Navbar />
    <Router>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path='/adicionar' element={<NewVehicle/>}/>
      </Routes>
    </Router>
    </div>
  );
}

export default App;
