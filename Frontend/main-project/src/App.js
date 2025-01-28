import React from 'react';
import {  BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import Navbar from './Components/Navbar';
import NewVehicle from './Pages/NewVehicle'
import EditVehicle from './Components/EditVehicle';
import Footer from './Components/Footer';


function App() {
  return (
    <div className="App">
      <Navbar />
    <Router>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path='/adicionar' element={<NewVehicle/>}/>
      <Route path='/editar/:id' element={<EditVehicle/>}/>
      </Routes>
    </Router>
    <Footer/>
    </div>
  );
}

export default App;
