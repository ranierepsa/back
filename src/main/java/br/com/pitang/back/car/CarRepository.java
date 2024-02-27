package br.com.pitang.back.car;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, UUID> {

}
