package com.danielgkneto.mcjavabc.cardealer;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CategoryRepository extends CrudRepository<Category, Long> {
//    ArrayList<Car> findByManufacturerContainingIgnoreCaseOrModelContainingIgnoreCase(String manufacturer, String model);
}
