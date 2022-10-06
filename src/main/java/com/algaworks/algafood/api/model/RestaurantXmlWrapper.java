package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("restaurants")
public class RestaurantXmlWrapper {

    @NonNull
    @JsonProperty("restaurant")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Restaurant> restaurants;

}
