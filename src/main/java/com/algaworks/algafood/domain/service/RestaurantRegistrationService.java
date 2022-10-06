package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.infrastructure.repository.KitchenRepository;
import com.algaworks.algafood.infrastructure.repository.RestaurantRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantRegistrationService {

    private RestaurantRepository restaurantRepository;

    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        Kitchen kitchenExists = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de cozinha com código %d", kitchenId)));

        restaurant.setKitchen(kitchenExists);

        return restaurantRepository.save(restaurant);

    }
}
