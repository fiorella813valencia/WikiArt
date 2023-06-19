package com.example.wikiart.application.provider.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProviderResource {
    //va lo que se manipular
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String apiUrl;
    private Boolean keyRequired=false;
    private String apiKey;
}
