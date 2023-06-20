package com.example.wikiart.application.author.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProviderResource {
    private Long id;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String apiUrl;
    private Boolean keyRequired=false;
    @NotBlank
    @NotNull
    private String apiKey;
}
