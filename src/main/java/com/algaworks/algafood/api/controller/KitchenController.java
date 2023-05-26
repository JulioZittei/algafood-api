package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.KitchenXmlWrapper;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.service.KitchenRegistrationService;
import com.algaworks.algafood.infrastructure.repository.KitchenRepository;
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
@RequestMapping("/kitchens")
public class KitchenController {

    private KitchenRepository kitchenRepository;

    private KitchenRegistrationService kitchenService;

    @GetMapping
    public List<Kitchen> getAllKitchens() {
        return kitchenRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchenXmlWrapper getAllKitchensXml() {
        return new KitchenXmlWrapper(kitchenRepository.findAll());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> getKitchen(@PathVariable Long kitchenId) {
        Optional<Kitchen> optionalKitchen = kitchenRepository.findById(kitchenId);
        if (optionalKitchen.isPresent()) {
            return ResponseEntity.ok(optionalKitchen.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Kitchen> postKitchen(@RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchen));
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> putKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        return ResponseEntity.ok(kitchenService.update(kitchenId, kitchen));
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> deleteKitchen(@PathVariable Long kitchenId) {
        kitchenService.delete(kitchenId);
        return ResponseEntity.noContent().build();
    }
}
