import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FilterIcon } from '@heroicons/react/solid'; 

const VehicleFilter = ({ setFilteredVehicles }) => {
  const [filters, setFilters] = useState({
    type: '', 
    modelo: '',
    cor: '',
    ano: ''
  });

  const [isFilterOpen, setIsFilterOpen] = useState(false); 
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFilters({
      ...filters,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const filterDataToSend = {
      tipo: filters.tipo,
      modelo: filters.modelo,
      cor: filters.cor,
      ano: filters.ano
    };
  
    try {
      const queryParams = new URLSearchParams(filterDataToSend).toString();
      const response = await fetch(`/filtrar?${queryParams}`);
      
      // Verificando o conteúdo retornado pela API
      const text = await response.text();
      console.log("Resposta da API:", text);
  
      const data = JSON.parse(text); // Tente usar JSON.parse caso a resposta não seja automaticamente convertida
  
      setFilteredVehicles(data);
      navigate('/resultados');
    } catch (error) {
      console.error('Erro ao filtrar veículos:', error);
    }
  };
  
  return (
    <div className="relative">
      {/* Botão de filtro com ícone */}
      <button
        onClick={() => setIsFilterOpen(!isFilterOpen)}
        className="p-3 bg-blue-950 text-white rounded-full hover:bg-blue-900 focus:outline-none absolute top-4 right-4"
      >
        <FilterIcon className="w-6 h-6" />
      </button>

      {/* Formulário de filtro */}
      {isFilterOpen && (
        <div className="max-w-lg mx-auto mt-8 p-6 bg-gray-300 rounded-lg shadow-md">
          <h2 className="text-2xl font-semibold text-center mb-4">Filtrar Veículos</h2>

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-sm mb-2 font-semibold" htmlFor="type">Tipo</label>
              <select
                id="type"  // Alterado de tipo para type
                name="type" // Alterado de tipo para type
                className="w-full p-2 border mb-2 rounded"
                value={filters.type} // Alterado de tipo para type
                onChange={handleChange}
              >
                <option value="">Selecione o tipo de veículo</option>
                <option value="Carro">Carro</option>
                <option value="Moto">Moto</option>
              </select>
            </div>

            <div className="mb-4">
              <label className="block mb-2 text-sm font-semibold" htmlFor="modelo">Modelo</label>
              <input
                type="text"
                id="modelo"
                name="modelo"
                className="w-full p-2 border border-gray-300 rounded"
                value={filters.modelo}
                onChange={handleChange}
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm mb-2 font-semibold" htmlFor="cor">Cor</label>
              <input
                type="text"
                id="cor"
                name="cor"
                className="w-full p-2 border border-gray-300 rounded"
                value={filters.cor}
                onChange={handleChange}
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm mb-2 font-semibold" htmlFor="ano">Ano</label>
              <input
                type="number"
                id="ano"
                name="ano"
                className="w-full p-2 border border-gray-300 rounded"
                value={filters.ano}
                onChange={handleChange}
              />
            </div>

            <div className="flex justify-center">
              <button
                type="submit"
                className="bg-blue-950 text-white py-2 px-6 rounded hover:bg-blue-900"
              >
                Filtrar Veículos
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
};

export default VehicleFilter;
