package com.example.wikiart.application.provider.domain.persistence;

import com.example.wikiart.application.provider.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findAll();
    Optional<Provider> findByName(String name);
}