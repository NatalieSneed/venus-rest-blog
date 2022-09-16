package sneed.venusrestblog.data;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sneed.venusrestblog.data.Post;
import sneed.venusrestblog.data.Category;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sneed.venusrestblog.data.respository.CategoriesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoriesController {
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    private List<Category> fetchAllCategories(@RequestParam String categoryName) {
//        if(categoryName != null & categoryName.length() > 0) {
//            Category cat = categoriesRepository.findByName(categoryName);
//            if(cat == null){
//                return null;
//            }
//            return new ArrayList<>(List.of(categoriesRepository.findByName(categoryName)));
//        }
        return categoriesRepository.findAll();
    }
    @GetMapping("/search")
    private Category fetchCategoryByCategoryName(@RequestParam String categoryName) {
        Category cat = categoriesRepository.findByName(categoryName);
        if(cat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + categoryName + " not found");
        }
        return cat;
    }
}

