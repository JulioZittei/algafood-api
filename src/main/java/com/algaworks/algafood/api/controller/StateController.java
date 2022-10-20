package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.StateXmlWrapper;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateRegistrationService;
import com.algaworks.algafood.infrastructure.repository.StateRepository;
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
@RequestMapping("/states")
public class StateController {

    private StateRepository stateRepository;

    private StateRegistrationService stateService;

    @GetMapping
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public StateXmlWrapper getAllStatesXml() {
        return new StateXmlWrapper(stateRepository.findAll());
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> getState(@PathVariable Long stateId) {
        Optional<State> optionalState = stateRepository.findById(stateId);
        if (optionalState.isPresent()) {
            return ResponseEntity.ok(optionalState.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<State> postSate(@RequestBody State state) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(state));
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> putState(@PathVariable Long stateId, @RequestBody State state) {
        return ResponseEntity.ok(stateService.update(stateId, state));
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> deleteState(@PathVariable Long stateId) {
        stateService.delete(stateId);
        return ResponseEntity.noContent().build();
    }
}
