package com.example.wikiart.application.provider.service;

import com.example.wikiart.application.provider.domain.model.Provider;
import com.example.wikiart.application.provider.domain.persistence.ProviderRepository;
import com.example.wikiart.application.provider.domain.service.ProviderService;
import com.example.wikiart.shared.exception.ResourceNotFoundException;
import com.example.wikiart.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProviderServiceImpl implements ProviderService {

    private static final String ENTITY="Provider";
    private final ProviderRepository providerRepository;
    private final Validator validator;

    public ProviderServiceImpl(ProviderRepository authorRepository, Validator validator){
        this.providerRepository=authorRepository;
        this.validator=validator;
    }


    @Override
    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    @Override
    public Page<Provider> getAll(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Provider getById(Long providerId) {
        return providerRepository.findById(providerId).orElseThrow(()->new ResourceNotFoundException(ENTITY,providerId));
    }

    @Override
    public Provider create(Provider provider) {

        Set<ConstraintViolation<Provider>> violations=validator.validate(provider);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        Optional<Provider> providerWithName=providerRepository.findByName(provider.getName());

        if(providerWithName!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same name already exists");

        Provider providerWithApiUrl=providerRepository.findByApiUrl(provider.getApiUrl());

        if(providerWithApiUrl!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same api url already exists");


        return providerRepository.save(provider);
    }

    @Override
    public Provider update(Long providerId, Provider request) {
        Set<ConstraintViolation<Provider>> violations=validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        Optional<Provider> providerWithName=providerRepository.findByName(request.getName());

        if(providerWithName!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same name already exists");

        Provider providerWithApiUrl=providerRepository.findByApiUrl(request.getApiUrl());

        if(providerWithApiUrl!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same api url already exists");

        return providerRepository.findById(providerId).map(
                provider -> providerRepository.save(
                        provider.withName(request.getName()))
                                .withApiKey(request.getApiKey())
                                .withApiUrl(request.getApiUrl())).orElseThrow(()->new ResourceNotFoundException(ENTITY,providerId));
    }

    @Override
    public ResponseEntity<?> delete(Long providerId) {
        return providerRepository.findById(providerId).map(provider -> {
            providerRepository.delete(provider);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,providerId));
    }
}
