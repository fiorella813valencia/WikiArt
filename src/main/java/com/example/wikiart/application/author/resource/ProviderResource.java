package com.example.wikiart.application.author.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
