import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/veiculo',
});

export const getAllVehicles = () => api.get();
export const createVehicle = (vehicleData) => api.post('/novo', vehicleData);
export const updateVehicle = (id, vehicleData) => api.put(`/${id}`, vehicleData);
export const deleteVehicle = (id) => api.delete(`/${id}`);
export const getVehicleById = (id) => api.get(`${id}`); 


export const filterVehicle = (params) => {
  const queryParams = new URLSearchParams(params).toString(); 
  return api.get(`/filtrar?${queryParams}`);
};

export default api;
