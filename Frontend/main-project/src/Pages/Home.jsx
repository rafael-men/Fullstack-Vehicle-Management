import React from 'react';
import { Link } from 'react-router-dom';
import VehicleTable from '../Components/VehicleTable';

const Home = () => {
  return (
    <div className="container mx-auto p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Lista de Veículos</h1>
        <Link
          to="/adicionar"
          className="bg-blue-950 text-white px-6 py-3 rounded-md shadow hover:bg-blue-800 transition duration-200"
        >
          Adicionar Novo Veículo
        </Link>
      </div>
      <VehicleTable/>
    </div>
  );
};

export default Home;
