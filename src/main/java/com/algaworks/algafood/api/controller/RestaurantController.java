package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.RestaurantXmlWrapper;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantRegistrationService;
import com.algaworks.algafood.infrastructure.repository.RestaurantRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantRepository restaurantRepository;

    private RestaurantRegistrationService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public RestaurantXmlWrapper getAllRestaurantsXml() {
        return new RestaurantXmlWrapper(restaurantRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurantExists = restaurantRepository.findById(id);
        if (restaurantExists.isPresent()) {
            return ResponseEntity.ok(restaurantExists.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurant));
    }
}
