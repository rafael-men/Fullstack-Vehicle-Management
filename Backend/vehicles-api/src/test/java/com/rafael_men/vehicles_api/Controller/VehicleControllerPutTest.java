package com.rafael_men.vehicles_api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael_men.vehicles_api.Request.VehicleUpdateRequest;
import com.rafael_men.vehicles_api.Service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class VehicleControllerPutTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUpdateCar() throws Exception {
        // criando uma requisição para atualizar um carro
        VehicleUpdateRequest carRequest = new VehicleUpdateRequest();
        carRequest.setType("Carro");
        carRequest.setModelo("Civic");
        carRequest.setCor("Preto");
        carRequest.setFabricante("Honda");
        carRequest.setAno(2022);
        carRequest.setPreco(95000.0);
        carRequest.setQuantidadePortas(4);
        carRequest.setTipoCombustivel("Gasolina");

        // configurando a conversão para JSON
        String carRequestJson = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(put("/veiculo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Veículo com ID 1 atualizado com sucesso."));

        // verificando se o serviço foi chamado corretamente para Carro
        verify(service, times(1)).updateCar(
                eq(1),
                eq("Civic"),
                eq("Preto"),
                eq("Honda"),
                eq(2022),
                eq(95000.0),
                eq(4),
                eq("Gasolina")
        );
    }

    @Test
    public void testUpdateMotorbike() throws Exception {
        // criando uma requisição para atualizar uma moto
        VehicleUpdateRequest motorbikeRequest = new VehicleUpdateRequest();
        motorbikeRequest.setType("Moto");
        motorbikeRequest.setModelo("CB 500X");
        motorbikeRequest.setCor("Vermelho");
        motorbikeRequest.setFabricante("Honda");
        motorbikeRequest.setAno(2021);
        motorbikeRequest.setPreco(40000.0);
        motorbikeRequest.setCilindrada(500);

        // Configurando a conversão para JSON
        String motorbikeRequestJson = objectMapper.writeValueAsString(motorbikeRequest);

        mockMvc.perform(put("/veiculo/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(motorbikeRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Veículo com ID 2 atualizado com sucesso."));

        // verificando se o serviço foi chamado corretamente para Moto
        verify(service, times(1)).updateMotorbike(
                eq(2),
                eq("CB 500X"),
                eq("Vermelho"),
                eq("Honda"),
                eq(2021),
                eq(40000.0),
                eq(500)
        );
    }

    @Test
    public void testInvalidVehicleType() throws Exception {
        // criando uma requisição com um tipo inválido
        VehicleUpdateRequest invalidRequest = new VehicleUpdateRequest();
        invalidRequest.setType("Caminhão");
        invalidRequest.setModelo("Truck");
        invalidRequest.setCor("Azul");
        invalidRequest.setFabricante("Volvo");
        invalidRequest.setAno(2023);
        invalidRequest.setPreco(120000.0);

        // configurando a conversão para JSON
        String invalidRequestJson = objectMapper.writeValueAsString(invalidRequest);

        // executando a requisição PUT
        mockMvc.perform(put("/veiculo/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Tipo de veículo inválido. Deve ser 'Carro' ou 'Moto'."));

        // verificando que nenhum serviço foi chamado
        verifyNoInteractions(service);
    }

    @Test
    public void testMissingMotorbikeCilindrada() throws Exception {
        // criando uma requisição para moto sem cilindrada
        VehicleUpdateRequest motorbikeRequest = new VehicleUpdateRequest();
        motorbikeRequest.setType("Moto");
        motorbikeRequest.setModelo("CB 500X");
        motorbikeRequest.setCor("Vermelho");
        motorbikeRequest.setFabricante("Honda");
        motorbikeRequest.setAno(2021);
        motorbikeRequest.setPreco(40000.0);
        motorbikeRequest.setCilindrada(null);

        // configurando a conversão para JSON
        String motorbikeRequestJson = objectMapper.writeValueAsString(motorbikeRequest);

        mockMvc.perform(put("/veiculo/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(motorbikeRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("A cilindrada é obrigatória para motos."));

        // verificando que o serviço não foi chamado
        verifyNoInteractions(service);
    }
}
