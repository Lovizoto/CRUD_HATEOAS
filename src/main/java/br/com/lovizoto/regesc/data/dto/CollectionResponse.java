package br.com.lovizoto.regesc.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

public class CollectionResponse<T> extends RepresentationModel<CollectionResponse<T>> {

    @JsonProperty("content")
    private final Collection<T> content;

    public CollectionResponse(Collection<T> content) {
        this.content = content;
    }

    public Collection<T> getContent() {
        return content;
    }

}
