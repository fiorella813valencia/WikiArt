package com.example.wikiart.application.provider.domain.service;

import com.example.wikiart.application.provider.domain.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProviderService {
    //We got the CRUD here for providers !!
    List<Provider> getAll();
    Page<Provider> getAll(Pageable pageable);
    Provider getById(Long providerId);
    Provider create(Provider provider);
    Provider update(Provider provider, Provider request);
    ResponseEntity<?> delete(Long providerId);
    //avoids the need to specify a specific type in the interface,
    // this ? allows the delete method to be more generic and supports different response


}
