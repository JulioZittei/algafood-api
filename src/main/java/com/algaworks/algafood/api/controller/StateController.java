package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.KitchenXmlWrapper;
import com.algaworks.algafood.api.model.StateXmlWrapper;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.infrastructure.repository.StateRepository;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
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
@RequestMapping("/states")
public class StateController {

    private StateRepository stateRepository;

    @GetMapping
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public StateXmlWrapper getAllStatesXml() {
        return new StateXmlWrapper(stateRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getState(@PathVariable Long id) {
        Optional<State> optionalState =  stateRepository.findById(id);
        if (optionalState.isPresent()) {
            return ResponseEntity.ok(optionalState.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<State> postSate(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateRepository.save(state));
    }
}
