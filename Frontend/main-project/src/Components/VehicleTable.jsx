import React, { useEffect, useState } from 'react';
import { getAllVehicles, deleteVehicle,  filterVehicle } from '../Services/Api';
import { Link } from 'react-router-dom';

const VehicleTable = () => {
  const [vehicles, setVehicles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [filters, setFilters] = useState({ tipo: '', modelo: '', cor: '', ano: '' })

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await getAllVehicles('/veiculo'); // Endpoint específico
      setVehicles(response.data);
    } catch (error) {
      setError('Erro ao buscar veículos. Tente novamente mais tarde.');
    } finally {
      setLoading(false);
    }
  };

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleFilterSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await filterVehicle(filters);
      setVehicles(response.data);
    } catch (error) {
      setError('Erro ao filtrar veículos. Tente novamente mais tarde.');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja excluir este veículo?')) {
      try {
        await deleteVehicle(id);
        fetchVehicles(); // Atualiza a lista
      } catch (error) {
        alert('Erro ao excluir o veículo. Tente novamente mais tarde.');
      }
    }
  };

  if (loading) {
    return <div className="text-gray-500 text-xl">Carregando...</div>;
  }

  if (error) {
    return <div className="text-red-500 mb-4">{error}</div>;
  }

  return (
    <div className="overflow-x-auto shadow-lg rounded-lg">
       <form
        onSubmit={handleFilterSubmit}
        className="mb-6 p-4 bg-slate-600 rounded-lg flex flex-wrap gap-4"
      >
        <input
          type="text"
          name="tipo"
          placeholder="Tipo"
          value={filters.tipo}
          onChange={handleFilterChange}
          className="p-2 rounded-md shadow-sm border border-gray-300"
        />
        <input
          type="text"
          name="modelo"
          placeholder="Modelo"
          value={filters.modelo}
          onChange={handleFilterChange}
          className="p-2 rounded-md shadow-sm border border-gray-300"
        />
        <input
          type="text"
          name="cor"
          placeholder="Cor"
          value={filters.cor}
          onChange={handleFilterChange}
          className="p-2 rounded-md shadow-sm border border-gray-300"
        />
        <input
          type="number"
          name="ano"
          placeholder="Ano"
          value={filters.ano}
          onChange={handleFilterChange}
          className="p-2 rounded-md shadow-sm border border-gray-300"
        />
        <button
          type="submit"
          className="bg-blue-950 text-white px-4 py-2 rounded-md shadow hover:bg-blue-800 transition duration-200"
        >
          Filtrar
        </button>
      </form>
      <table className="min-w-full bg-slate-500 table-auto">
        <thead className="bg-slate-900">
          <tr>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Modelo</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Fabricante</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Ano</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Preço</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Cor</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Qtd. de Portas</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Tipo de Combustível</th>
            <th className="px-4 py-2 text-left text-sm font-medium text-white">Cilindrada</th>
            <th className="px-4 py-2 text-center text-sm font-medium text-white">Ações</th>
          </tr>
        </thead>
        <tbody>
          {vehicles.map((vehicle) => (
            <tr key={vehicle.id} className="border-t border-gray-2000">
              <td className="px-4 py-2 text-sm text-white">{vehicle.modelo}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.fabricante}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.ano}</td>
              <td className="px-4 py-2 text-sm text-white">R$ {vehicle.preco.toFixed(2)}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.cor || 'N/A'}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.quantidadePortas || 'N/A'}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.tipoCombustivel || 'N/A'}</td>
              <td className="px-4 py-2 text-sm text-white">{vehicle.cilindrada || 'N/A'}</td>
              <td className="px-4 py-2 text-center text-sm">
             
                <Link
                 to={`/editar/${vehicle.id}`}
                 className="bg-blue-500 text-white px-4  mt-4 rounded-md shadow hover:bg-blue-600 transition duration-200"
               >
                 Editar
               </Link>
               <button
                 onClick={() => handleDelete(vehicle.id)}
                 className="bg-red-500 text-white px-4  mt-3 rounded-md shadow hover:bg-red-600 transition duration-200"
               >
                 Excluir
               </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default VehicleTable;
