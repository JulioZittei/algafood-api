package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.infrastructure.repository.KitchenRepository;
import com.algaworks.algafood.infrastructure.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantRegistrationService {

    private RestaurantRepository restaurantRepository;

    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        Kitchen kitchenExists = kitchenRepository.findById(kitchenId).orElseThrow(() -> new EntityNotFoundException(
                String.format("Não existe um cadastro de cozinha com código %d", kitchenId)));

        restaurant.setKitchen(kitchenExists);

        return restaurantRepository.save(restaurant);

    }

    public Restaurant update(Long restaurantId, Restaurant restaurant) {

        Restaurant restaurantExists = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de restaurante com código %d", restaurantId)));

        Long kitchenId = restaurant.getKitchen().getId() != null
                ? restaurant.getKitchen().getId() : restaurantExists.getKitchen().getId();

        Kitchen kitchenExists = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de cozinha com código %d", kitchenId)));

        restaurantExists.setKitchen(kitchenExists);

        BeanUtils.copyProperties(restaurant, restaurantExists, "id", "kitchen");

        return restaurantRepository.save(restaurantExists);
    }

    public void delete(Long restaurantId) {
        try {
            restaurantRepository.deleteById(restaurantId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    String.format("Não existe um cadastro de restaurante com código %d", restaurantId));
        }
    }
}

