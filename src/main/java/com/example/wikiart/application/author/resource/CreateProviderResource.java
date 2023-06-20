package com.example.wikiart.application.author.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



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
