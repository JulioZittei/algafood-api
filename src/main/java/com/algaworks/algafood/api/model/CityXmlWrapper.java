package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.City;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("cities")
public class CityXmlWrapper {


    @NonNull
    @JsonProperty("city")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<City> cities;
}
