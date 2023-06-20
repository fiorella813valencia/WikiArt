package com.example.wikiart.application.author.mapping;

import org.springframework.context.annotation.Bean;
import com.example.wikiart.application.author.mapping.AuthorMapper;
import com.example.wikiart.application.author.mapping.ProviderMapper;

public class MappingConfiguration {
    @Bean
    public AuthorMapper authorMapper(){return  new AuthorMapper();}
    @Bean
    public ProviderMapper providerMapper(){return new ProviderMapper();}

}

