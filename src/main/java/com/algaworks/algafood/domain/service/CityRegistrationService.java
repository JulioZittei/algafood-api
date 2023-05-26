package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.infrastructure.repository.CityRepository;
import com.algaworks.algafood.infrastructure.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityRegistrationService {

    private CityRepository cityRepository;

    private StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();

        State stateExists = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Não existe um cadastro de estado com código %d", stateId)));

        city.setState(stateExists);

        return cityRepository.save(city);
    }

    public City update(Long cityId, City city) {

        City cityExists = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de cidade com código %d", cityId)));

        Long stateId = city.getState().getId() != null
                ? city.getState().getId() : cityExists.getState().getId();

        State stateExists = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de cozinha com código %d", stateId)));

        cityExists.setState(stateExists);

        BeanUtils.copyProperties(city, cityExists, "id", "state");

        return cityRepository.save(cityExists);
    }

    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de cidade com código %d", cityId));
        }
    }
}
