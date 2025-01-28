package com.rafael_men.vehicles_api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael_men.vehicles_api.Model.Car;
import com.rafael_men.vehicles_api.Service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VehicleControllerPostTest {

    private MockMvc mockMvc;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSaveVehicle() throws Exception {
        Car car = new Car();
        car.setModelo("Civic");
        car.setFabricante("Honda");
        car.setAno(2022);
        car.setPreco(150000.00);
        car.setTipoCombustivel("Gasolina");
        car.setQuantidadePortas(4);

        // mockando o método do serviço saveNew
        when(vehicleService.saveNew(car)).thenReturn(car);

        mockMvc.perform(post("/veiculo/novo")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk());
    }


}
