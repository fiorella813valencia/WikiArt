package com.example.wikiart.application.provider.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResource {
    private Long id;
    private String name;
    private String apiUrl;
    private Boolean keyRequired=false;
    private String apiKey;
}
