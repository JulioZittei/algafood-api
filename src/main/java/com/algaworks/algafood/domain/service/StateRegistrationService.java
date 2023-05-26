package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.infrastructure.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StateRegistrationService {

    private StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public State update(Long stateId, State state) {

        State stateExists = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de estado com código %d", stateId)));

        BeanUtils.copyProperties(state, stateExists, "id");

        return stateRepository.save(stateExists);
    }

    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException ex) {
            if (ex instanceof EmptyResultDataAccessException) {
                throw new EntityNotFoundException(
                        String.format("Não existe um cadastro de estado com código %d", stateId));
            } else if (ex instanceof DataIntegrityViolationException) {
                throw new EntityInUseException(
                        String.format("Estado de código %d não pode ser removida, pois está em uso", stateId));
            }
        }
    }
}
