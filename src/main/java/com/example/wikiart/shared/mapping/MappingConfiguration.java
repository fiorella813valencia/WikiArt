package com.example.wikiart.shared.mapping;

import com.example.wikiart.application.author.mapping.AuthorMapper;
import com.example.wikiart.application.provider.mapping.ProviderMapper;
import org.springframework.context.annotation.Bean;

public class MappingConfiguration {
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }

    @Bean
    public AuthorMapper AuthorMapper(){return new AuthorMapper();}

    @Bean
    public ProviderMapper ProviderMapper(){return new ProviderMapper();}


}
