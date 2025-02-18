package com.rafael_men.vehicles_api.Service;

import com.rafael_men.vehicles_api.Exceptions.VehicleNotFoundException;
import com.rafael_men.vehicles_api.Model.Car;
import com.rafael_men.vehicles_api.Model.Motorbike;
import com.rafael_men.vehicles_api.Model.Vehicle;
import com.rafael_men.vehicles_api.Repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle saveNew(@Valid Vehicle veiculo) {
        return repository.save(veiculo);
    }

    public List<Vehicle> getAllVehicles() {
        return repository.findAll();
    }

    public List<Vehicle> filterVehicles(String tipo, String modelo, String cor, Integer ano) {

        StringBuilder sql = new StringBuilder("SELECT * FROM veiculos WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (tipo != null && !tipo.trim().isEmpty()) {
            sql.append(" AND tipo_veiculo = ?");
            params.add(tipo.trim());
        }

        if (modelo != null && !modelo.trim().isEmpty()) {
            sql.append(" AND modelo LIKE ?");
            params.add("%" + modelo.trim() + "%");
        }

        if (cor != null && !cor.trim().isEmpty()) {
            sql.append(" AND cor LIKE ?");
            params.add("%" + cor.trim() + "%");
        }

        if (ano != null) {
            sql.append(" AND ano = ?");
            params.add(ano);
        }

        Object[] paramArray = params.toArray();

        List<Vehicle> vehicles = jdbcTemplate.query(sql.toString(), paramArray, (rs, rowNum) -> {
            String tipoVeiculo = rs.getString("tipo_veiculo");

            Vehicle vehicle;

            if ("CARRO".equalsIgnoreCase(tipoVeiculo)) {
                vehicle = new Car();
            } else if ("MOTO".equalsIgnoreCase(tipoVeiculo)) {
                vehicle = new Motorbike();
            } else {
                throw new IllegalArgumentException("Tipo de veículo desconhecido: " + tipoVeiculo);
            }

            vehicle.setId(rs.getInt("id"));
            vehicle.setModelo(rs.getString("modelo"));
            vehicle.setFabricante(rs.getString("fabricante"));
            vehicle.setCor(rs.getString("cor"));
            vehicle.setAno(rs.getInt("ano"));
            vehicle.setPreco(rs.getDouble("preco"));

            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                car.setQuantidadePortas(rs.getInt("quantidade_portas"));
                car.setTipoCombustivel(rs.getString("tipo_combustivel"));
            } else if (vehicle instanceof Motorbike) {
                Motorbike motorbike = (Motorbike) vehicle;
                motorbike.setCilindrada(rs.getInt("cilindrada"));
            }
            return vehicle;
        });
        return vehicles;
    }

    public void deleteVehicleById(int id) {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        System.out.println("Excluindo veículo com ID: " + id);
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected > 0) {
            System.out.println("Veículo com ID " + id + " excluído.");
        } else {
            System.out.println("Nenhum veículo encontrado com o ID " + id);
        }
    }

    public void updateCar(int id, String modelo, String cor, String fabricante, int ano, double preco, int quantidadePortas, String tipoCombustivel) {
        String sql = "UPDATE veiculos SET modelo = ?, cor = ?, fabricante = ?, ano = ?, preco = ?, quantidade_portas = ?, tipo_combustivel = ? WHERE id = ?";
        jdbcTemplate.update(sql, modelo, cor, fabricante, ano, preco, quantidadePortas, tipoCombustivel, id);
    }

    public void updateMotorbike(int id, String modelo, String cor, String fabricante, int ano, double preco, int cilindrada) {
        String sql = "UPDATE veiculos SET modelo = ?, cor = ?, fabricante = ?, ano = ?, preco = ?, cilindrada = ? WHERE id = ?";
        jdbcTemplate.update(sql, modelo, cor, fabricante, ano, preco, cilindrada, id);
    }

    public Vehicle getVehicleById(Long id) {
        Optional<Vehicle> veiculoOptional = repository.findById(id);

        if (veiculoOptional.isPresent()) {
            return veiculoOptional.get(); // Retorna o veículo se encontrado
        } else {
            throw new VehicleNotFoundException("Veículo não encontrado com o id: " + id); // Lançando exceção se não encontrado
        }
    }
}
