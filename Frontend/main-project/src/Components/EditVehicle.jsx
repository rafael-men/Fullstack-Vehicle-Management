import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getVehicleById, updateVehicle } from '../Services/Api'; 

const EditVehicle = () => {
  const { id } = useParams(); 
  const navigate = useNavigate();

  const [vehicleData, setVehicleData] = useState({
    tipo: '',
    modelo: '',
    cor: '',
    fabricante: '',
    ano: '',
    preco: '',
    quantidadePortas: '',
    tipoCombustivel: '',
    cilindrada: ''
  });

  const [error, setError] = useState(null);

  // Carregar os dados do veículo ao montar o componente
  useEffect(() => {
    const fetchVehicle = async () => {
      try {
        const response = await getVehicleById(id);
        setVehicleData(response.data); 
      } catch (err) {
        setError('Erro ao carregar os dados do veículo');
      }
    };

    fetchVehicle();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setVehicleData({
      ...vehicleData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    const { tipo, quantidadePortas, tipoCombustivel, cilindrada, ...commonData } = vehicleData;

    const vehicleDataToSend = {
      type: tipo,
      ...commonData,
    };

    if (tipo === "Carro") {
      vehicleDataToSend.quantidadePortas = quantidadePortas || 0;
      vehicleDataToSend.tipoCombustivel = tipoCombustivel || "N/A";
    } else if (tipo === "Moto") {
      vehicleDataToSend.cilindrada = cilindrada || 0;
    }

    try {
      await updateVehicle(id, vehicleDataToSend);
      alert("Veículo atualizado com sucesso!");
      navigate("/"); 
    } catch (err) {
      setError('Erro ao atualizar veículo.');
    }
  };

  return (
    <div className="max-w-lg mx-auto mt-8 p-6 bg-slate-400 rounded-lg shadow-md mb-10">
      <h2 className="text-2xl font-semibold text-center">Editar Veículo</h2>

      {error && <p className="text-red-500 text-center">{error}</p>}

      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label className="block text-sm mb-2 font-semibold" htmlFor="tipo">Tipo</label>
          <select
            id="tipo"
            name="tipo"
            className="w-full p-2 border mb-2 rounded"
            value={vehicleData.tipo}
            onChange={handleChange}
            required
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
            value={vehicleData.modelo}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-4">
          <label className="block text-sm mb-2 font-semibold" htmlFor="cor">Cor</label>
          <input
            type="text"
            id="cor"
            name="cor"
            className="w-full p-2 border border-gray-300 rounded"
            value={vehicleData.cor}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-4">
          <label className="block text-sm mb-2 font-semibold" htmlFor="fabricante">Fabricante</label>
          <input
            type="text"
            id="fabricante"
            name="fabricante"
            className="w-full p-2 border border-gray-300 rounded"
            value={vehicleData.fabricante}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-4">
          <label className="block text-sm mb-2 font-semibold" htmlFor="ano">Ano</label>
          <input
            type="number"
            id="ano"
            name="ano"
            className="w-full p-2 border border-gray-300 rounded"
            value={vehicleData.ano}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-4">
          <label className="block text-sm mb-2 font-semibold" htmlFor="preco">Preço</label>
          <input
            type="number"
            id="preco"
            name="preco"
            className="w-full p-2 border border-gray-300 rounded"
            value={vehicleData.preco}
            onChange={handleChange}
            required
          />
        </div>

        {vehicleData.tipo === 'Carro' && (
          <>
            <div className="mb-4">
              <label className="block text-sm font-semibold mb-2 " htmlFor="quantidadePortas">Quantidade de Portas</label>
              <input
                type="number"
                id="quantidadePortas"
                name="quantidadePortas"
                className="w-full p-2 border border-gray-300 rounded"
                value={vehicleData.quantidadePortas}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm font-semibold mb-2 " htmlFor="tipoCombustivel">Tipo de Combustível</label>
              <select
                id="tipoCombustivel"
                name="tipoCombustivel"
                className="w-full p-2 border border-gray-300 rounded"
                value={vehicleData.tipoCombustivel}
                onChange={handleChange}
                required
              >
                <option value="">Selecione o tipo de combustível</option>
                <option value="Gasolina">Gasolina</option>
                <option value="Etanol">Etanol</option>
                <option value="Diesel">Diesel</option>
                <option value="Flex">Flex</option>
              </select>
            </div>
          </>
        )}

        {vehicleData.tipo === 'Moto' && (
          <div className="mb-4">
            <label className="block text-sm mb-2 font-semibold" htmlFor="cilindrada">Cilindrada</label>
            <input
              type="number"
              id="cilindrada"
              name="cilindrada"
              className="w-full p-2 border border-gray-300 rounded"
              value={vehicleData.cilindrada}
              onChange={handleChange}
              required
            />
          </div>
        )}

        <div className="flex justify-center">
          <button
            type="submit"
            className="bg-blue-950 text-white py-2 px-6 rounded hover:bg-blue-900"
          >
            Atualizar Veículo
          </button>
        </div>
      </form>
    </div>
  );
};

export default EditVehicle;
