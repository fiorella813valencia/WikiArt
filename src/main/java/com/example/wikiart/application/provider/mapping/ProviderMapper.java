package com.example.wikiart.application.provider.mapping;

import com.example.wikiart.application.provider.domain.model.Provider;
import com.example.wikiart.application.provider.resource.CreateProviderResource;
import com.example.wikiart.application.provider.resource.ProviderResource;
import com.example.wikiart.shared.mapping.EnhancedModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProviderMapper implements Serializable {
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
}
