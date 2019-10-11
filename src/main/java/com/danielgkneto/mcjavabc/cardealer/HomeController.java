package com.danielgkneto.mcjavabc.cardealer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

        Category[] fakeCategories = new Category[] {new Category("Sedan", "A sedan is a jksldjsklajdklsajdlasjdjasldjsaljdlasjdsajldjasl."), new Category("SUV", "A SUV is a jksldjsklajdklsajdlasjdjasldjsaljdlasjdsajldjasl."), new Category("Mini", "A mini car is a jksldjsklajdklsajdlasjdjasldjsaljdlasjdsajldjasl."), new Category("Sport", "A sport car is a jksldjsklajdklsajdlasjdjasldjsaljdlasjdsajldjasl.")};
        categoryRepository.saveAll(Arrays.asList(fakeCategories));

        Car[] fakeCars = new Car[] {new Car("Toyota","Corolla","2010",11000, fakeCategories[0]), new Car("Ford","Mustang","2018",88000, fakeCategories[3]), new Car("Volkswagen","Beatle","1966",500, fakeCategories[2]), new Car("Erat Volutpat Company","Debra","7399",34925, fakeCategories[1]), new Car("Back to the Future","DeLorean","2000",100000, fakeCategories[3])};
        carRepository.saveAll(Arrays.asList(fakeCars));

        return "redirect:/";
    }

    @RequestMapping("/")
    public String carList(Model model){
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @RequestMapping("categories")
    public String categoryList(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

/*    @RequestMapping("/docs")
    public String docs(Model model){
        return "docs";
    }*/

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
        return "redirect:/categories";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/update/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/categoryupdate/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/categorydelete/{id}")
    public String deleteCategory(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }

    @RequestMapping("/pickcategory/{chosencategory}")
    public String pickCategory(@PathVariable("chosencategory") long chosenCategory, Model model){
        Category c = categoryRepository.findById(chosenCategory).get();
        model.addAttribute("cars", carRepository.findByCategory(c));
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        String[] keywords = search.split(" ");
        Set<Car> cars = new HashSet<Car>();

        for (int i = 0; i < keywords.length; i++) {
            cars.addAll(carRepository.findByManufacturerContainingIgnoreCaseOrModelContainingIgnoreCase(keywords[i], keywords[i]));
        }
        model.addAttribute("cars", cars);
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }
}
