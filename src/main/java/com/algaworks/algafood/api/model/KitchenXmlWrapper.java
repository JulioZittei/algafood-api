package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("kitchens")
public class KitchenXmlWrapper {

    @NonNull
    @JsonProperty("kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Kitchen> kitchens;
}
