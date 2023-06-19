package com.example.wikiart.application.author.mapping;

import com.example.wikiart.application.author.domain.model.Author;
import com.example.wikiart.application.author.resource.AuthorResource;
import com.example.wikiart.application.author.resource.CreateAuthorResource;
import com.example.wikiart.application.author.resource.UpdateAuthorResource;
import com.example.wikiart.shared.mapping.EnhancedModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AuthorMapper implements Serializable {
    EnhancedModelMapper mapper;

    //(CREATE) Se envia una entidad y se cambia a un recurso
    public AuthorResource toResource(Author model){
        return mapper.map(model, AuthorResource.class);
    }
    public Page<AuthorResource> modelListPage(List<Author> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,AuthorResource.class),pageable,modelList.size());

    }
    //(CREATE)se envia un recurso para que retorne entidad
    public Author toModel(CreateAuthorResource resource){
        return mapper.map(resource, Author.class);
    }

    //(UPDATE)se envia un recurso para que retorne entidad
    public Author toModel(UpdateAuthorResource resource){
        return mapper.map(resource,Author.class);
    }

}
