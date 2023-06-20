package com.example.wikiart.application.author.service;

import com.example.wikiart.application.author.domain.model.Provider;
import com.example.wikiart.application.author.domain.persistence.ProviderRepository;
import com.example.wikiart.application.author.domain.service.ProviderService;
import com.example.wikiart.shared.exception.ResourceNotFoundException;
import com.example.wikiart.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
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
        return providerRepository.findById(providerId).orElseThrow(()->new ResourceNotFoundException("ENTITY,providerId"));
    }

    @Override
    public Provider create(Provider provider) {

        Set<ConstraintViolation<Provider>> violations=validator.validate(provider);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        Optional<Provider> providerWithName=providerRepository.findByName(provider.getName());
        Provider providerWithSameName=providerRepository.findProviderByName(provider.getName());

        if(providerWithSameName!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same name already exists");

        Provider providerWithApiUrl=providerRepository.findByApiUrl(provider.getApiUrl());

        if(providerWithApiUrl!=null)
            throw new ResourceValidationException(ENTITY,"A provider with the same api url already exists");

        if (provider.getKeyRequired()) {
            throw new ResourceValidationException(ENTITY, "keyRequired cannot be set to true");
        }


        return providerRepository.save(provider);
    }

    @Override
    public Provider update(Long providerId, Provider request) {
        Set<ConstraintViolation<Provider>> violations=validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);


//        Provider existingProvider = providerRepository.findById(providerId)
//                .orElseThrow(() -> new ResourceNotFoundException("This Provider does not exist"));
//
//        if (!existingProvider.getName().equals(request.getName())) {
//            // the name has changed lets begin with the validations
//            Optional<Provider> providerWithName=providerRepository.findByName(request.getName());
//            //Provider providerWithSameName = providerRepository.findProviderByName(request.getName());
//            if (providerWithName.isPresent()) {
//                throw new ResourceValidationException(ENTITY, "A provider with the same name already exists");
//            }
//        }
//
//        if (!existingProvider.getApiUrl().equals(request.getApiUrl())) {
//            Provider providerWithApiUrl=providerRepository.findByApiUrl(request.getApiUrl());
//
//            if(providerWithApiUrl!=null)
//                throw new ResourceValidationException(ENTITY,"A provider with the same api url already exists");
//        }
//
//
//        if (request.getKeyRequired()) {
//            throw new ResourceValidationException(ENTITY, "keyRequired cannot be set to true");
//        }

//        return providerRepository.findById(providerId).map(
//                provider -> providerRepository.save(
//                        provider.withName(request.getName()))
//                                .withApiKey(request.getApiKey())
//                                .withApiUrl(request.getApiUrl())).orElseThrow(()->new ResourceNotFoundException("ENTITY,providerId"));

        Provider existingProvider = providerRepository.findById(providerId)
                .orElseThrow(() -> new ResourceNotFoundException("This Provider does not exist"));

        if (!existingProvider.getName().equals(request.getName())) {
            Optional<Provider> providerWithName = providerRepository.findByName(request.getName());
            if (providerWithName.isPresent()) {
                throw new ResourceValidationException(ENTITY, "A provider with the same name already exists");
            }
        }

        if (!existingProvider.getApiUrl().equals(request.getApiUrl())) {
            Provider providerWithApiUrl = providerRepository.findByApiUrl(request.getApiUrl());
            if (providerWithApiUrl != null) {
                throw new ResourceValidationException(ENTITY, "A provider with the same api url already exists");
            }
        }

        if (request.getKeyRequired()) {
            throw new ResourceValidationException(ENTITY, "keyRequired cannot be set to true");
        }

        existingProvider = existingProvider.withName(request.getName())
                .withApiUrl(request.getApiUrl())
                .withApiKey(request.getApiKey());

        Provider updatedProvider = providerRepository.save(existingProvider);
        return updatedProvider;
    }

    @Override
    public ResponseEntity<?> delete(Long providerId) {
        return providerRepository.findById(providerId).map(provider -> {
            providerRepository.delete(provider);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("ENTITY,providerId"));
    }
}
