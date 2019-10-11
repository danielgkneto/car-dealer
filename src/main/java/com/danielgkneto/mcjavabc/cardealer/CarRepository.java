package com.danielgkneto.mcjavabc.cardealer;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CarRepository extends CrudRepository<Car, Long> {
    ArrayList<Car> findByCategory(Category category);
    ArrayList<Car> findByManufacturerContainingIgnoreCaseOrModelContainingIgnoreCase(String manufacturer, String model);
}
