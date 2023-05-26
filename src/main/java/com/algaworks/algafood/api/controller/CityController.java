package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CityXmlWrapper;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.service.CityRegistrationService;
import com.algaworks.algafood.infrastructure.repository.CityRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private CityRepository cityRepository;

    private CityRegistrationService cityService;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CityXmlWrapper getAllCitiesXml() {
        return new CityXmlWrapper(cityRepository.findAll());
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> getCity(@PathVariable Long cityId) {
        Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            return ResponseEntity.ok(optionalCity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<City> postCity(@RequestBody City city) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<City> putCity(@PathVariable Long cityId, @RequestBody City city) {
        return ResponseEntity.ok(cityService.update(cityId, city));
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable Long cityId) {
        cityService.delete(cityId);
        return ResponseEntity.noContent().build();
    }
}
