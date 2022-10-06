package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.infrastructure.repository.KitchenRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KitchenRegistrationService {

    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void delete(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException | DataIntegrityViolationException ex) {
            if (ex instanceof EmptyResultDataAccessException) {
                throw new EntityNotFoundException(String.format("Não existe um cadastro de cozinha com código %d", kitchenId));
            } else {
                throw new EntityInUseException(
                        String.format("Cozinha de código %d não pode ser removida, pois está em uso", kitchenId));
            }
        }
    }
}
