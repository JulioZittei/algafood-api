package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
