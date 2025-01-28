package com.rafael_men.vehicles_api.Controller;


import com.rafael_men.vehicles_api.Exceptions.VehicleNotFoundException;
import com.rafael_men.vehicles_api.Model.Car;
import com.rafael_men.vehicles_api.Model.Vehicle;
import com.rafael_men.vehicles_api.Service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VehicleControllerGetTest {

    private MockMvc mockMvc;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void testGetAllVehicles() throws Exception {
        Car car = new Car();
        car.setModelo("Civic");


        List<Vehicle> vehicleList = Arrays.asList(car);

        // mockando o serviço getAllVehicles
        when(vehicleService.getAllVehicles()).thenReturn(vehicleList);

        mockMvc.perform(get("/veiculo"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVehicleById() throws Exception {

        Vehicle vehicle = new Car();
        vehicle.setId(1); // id do veículo
        vehicle.setModelo("Civic");

        // mockando o serviço para retornar o veículo pelo ID
        when(vehicleService.getVehicleById(1L)).thenReturn(vehicle);

        // realizando a requisição GET para o endpoint /veiculo/{id}
        mockMvc.perform(get("/veiculo/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVehicleByIdNotFound() throws Exception {
        // mockando o serviço para lançar exceção quando não encontrar o veículo
        when(vehicleService.getVehicleById(99L)).thenThrow(new VehicleNotFoundException("Vehicle not found with id: 99"));
        mockMvc.perform(get("/veiculo/99"))
                .andExpect(status().isNotFound());
    }

}
