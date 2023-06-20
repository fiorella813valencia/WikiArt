package com.example.wikiart.application.author.mapping;

import com.example.wikiart.application.author.domain.model.Provider;
import com.example.wikiart.application.author.resource.CreateProviderResource;
import com.example.wikiart.application.author.resource.ProviderResource;
import com.example.wikiart.application.author.resource.UpdateProviderResource;
import com.example.wikiart.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@EnableAutoConfiguration
public class ProviderMapper implements Serializable {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    EnhancedModelMapper mapper;

    //Se envia una entidad y se cambia a un recurso
    public ProviderResource toResource(Provider model){
        return mapper.map(model,ProviderResource.class);
    }

    public Page<ProviderResource> modelListPage(List<Provider> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ProviderResource.class),pageable,modelList.size());
    }

    //se envia un recurso para que retorne entidad
    public Provider toModel(CreateProviderResource resource){
        return mapper.map(resource,Provider.class);
    }

    public Provider toModel(UpdateProviderResource resource){
        return mapper.map(resource,Provider.class);
    }
}
