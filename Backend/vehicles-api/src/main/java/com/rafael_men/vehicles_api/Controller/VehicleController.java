package com.rafael_men.vehicles_api.Controller;

import com.rafael_men.vehicles_api.Model.Car;
import com.rafael_men.vehicles_api.Model.Motorbike;
import com.rafael_men.vehicles_api.Model.Vehicle;
import com.rafael_men.vehicles_api.Request.VehicleUpdateRequest;
import com.rafael_men.vehicles_api.Service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping("/novo")
    @Operation(summary = "Cadastrar Veículo", description = "Cadastra um veículo novo.")
    public Vehicle saveVehicle(@RequestBody Vehicle veiculo) {
        if (!(veiculo instanceof Car) && !(veiculo instanceof Motorbike)) {
            throw new IllegalArgumentException("Tipo de veículo inválido. Deve ser 'Carro' ou 'Moto'.");
        }

        // valida a cilindrada obrigatória para moto
        if (veiculo instanceof Motorbike) {
            Motorbike motorbike = (Motorbike) veiculo;
            if (motorbike.getCilindrada() == null || motorbike.getCilindrada() <= 0) {
                throw new IllegalArgumentException("A cilindrada é obrigatória para motos.");
            }
        }

        if (veiculo instanceof Car) {
            // valida os atributos obrigatórios do carro
            Car car = (Car) veiculo;
            if (!"Gasolina".equalsIgnoreCase(car.getTipoCombustivel()) &&
                    !"Etanol".equalsIgnoreCase(car.getTipoCombustivel()) &&
                    !"Diesel".equalsIgnoreCase(car.getTipoCombustivel()) &&
                    !"Flex".equalsIgnoreCase(car.getTipoCombustivel())) {
                throw new IllegalArgumentException("Tipo de combustível inválido para Carro. Deve ser 'Gasolina', 'Etanol','Diesel' ou 'Flex'.");
            }
            if(car.getTipoCombustivel() == null) {
                throw new IllegalArgumentException("Tipo de combustível obrigatório.");
            }
            if(car.getQuantidadePortas() == null || car.getQuantidadePortas() == 0) {
                throw new IllegalArgumentException("Número de portas obrigatório.");
            }
        }
        return service.saveNew(veiculo);
    }

    @GetMapping
    @Operation(summary = "Obter todos os veículos", description = "Retorna uma lista de todos os veículos cadastrados.")
    public List<Vehicle> getAllVehicles() {
        return service.getAllVehicles();
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtrar veículos", description = "Filtra veículos por tipo, modelo, cor e ano.")
    public List<Vehicle> getVehiclesByTypeColorModelYear(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer ano) {

        return service.filterVehicles(tipo, modelo, cor, ano);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Veículo por id", description = "Deleta um veículo selecionado por id.")
    public String deleteVehicle(@PathVariable int id) {
        service.deleteVehicleById(id);
        return "Veículo com ID " + id + " excluído.";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veículo por id", description = "Atualiza um veículo pelo seu id.")
    public String updateVehicle(@PathVariable int id, @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {

        System.out.println("Tipo de veículo: " + vehicleUpdateRequest.getType());

        String tipoVeiculo = vehicleUpdateRequest.getType();

        if (!"Carro".equalsIgnoreCase(tipoVeiculo) && !"Moto".equalsIgnoreCase(tipoVeiculo)) {
            return "Tipo de veículo inválido. Deve ser 'Carro' ou 'Moto'.";
        }

        if ("Carro".equalsIgnoreCase(tipoVeiculo)) {
            service.updateCar(id,
                    vehicleUpdateRequest.getModelo(),
                    vehicleUpdateRequest.getCor(),
                    vehicleUpdateRequest.getFabricante(),
                    vehicleUpdateRequest.getAno(),
                    vehicleUpdateRequest.getPreco(),
                    vehicleUpdateRequest.getQuantidadePortas(),
                    vehicleUpdateRequest.getTipoCombustivel());
        } else if ("Moto".equalsIgnoreCase(tipoVeiculo)) {
            // verifica se a cilindrada foi fornecida
            if (vehicleUpdateRequest.getCilindrada() == null || vehicleUpdateRequest.getCilindrada() <= 0) {
                return "A cilindrada é obrigatória para motos.";
            }

            service.updateMotorbike(id,
                    vehicleUpdateRequest.getModelo(),
                    vehicleUpdateRequest.getCor(),
                    vehicleUpdateRequest.getFabricante(),
                    vehicleUpdateRequest.getAno(),
                    vehicleUpdateRequest.getPreco(),
                    vehicleUpdateRequest.getCilindrada());
        }
        return "Veículo com ID " + id + " atualizado com sucesso.";
    }
}
