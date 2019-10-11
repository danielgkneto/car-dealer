package com.danielgkneto.mcjavabc.cardealer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    CarRepository carRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/f")
    public String fillTable() {
// TODO fill database
        return "redirect:/";
    }

    @RequestMapping("/")
    public String carList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }

    @RequestMapping("/docs")
    public String docs(Model model){
        return "docs";
    }

    @GetMapping("/addcar")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processcar")
    public String saveCar(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping("/processcategory")
    public String saveCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        String[] keywords = search.split(" ");
        Set<Car> cars = new HashSet<Car>();

        for (int i = 0; i < keywords.length; i++) {
            cars.addAll(carRepository.findByManufacturerContainingIgnoreCaseOrModelContainingIgnoreCase(keywords[i], keywords[i]));
        }
        model.addAttribute("cars", cars);
        return "index";
    }
}
