package com.example.wikiart.application.author.domain.persistence;

import com.example.wikiart.application.author.domain.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findAll();
    Optional<Provider> findByName(String name);

    Provider findByApiUrl(String apiUrl);


}
