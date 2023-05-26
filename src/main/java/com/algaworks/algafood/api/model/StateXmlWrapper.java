package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("states")
public class StateXmlWrapper {

    @NonNull
    @JsonProperty("state")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<State> states;
}
