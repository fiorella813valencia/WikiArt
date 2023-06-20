package com.example.wikiart.application.author.service;

import com.example.wikiart.application.author.domain.model.Author;
import com.example.wikiart.application.author.domain.persistence.AuthorRepository;
import com.example.wikiart.application.author.domain.service.AuthorService;
import com.example.wikiart.shared.exception.ResourceNotFoundException;
import com.example.wikiart.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String ENTITY="Author";
    private final AuthorRepository authorRepository;
    private final Validator validator;

    public AuthorServiceImpl(AuthorRepository authorRepository, Validator validator){
        this.authorRepository=authorRepository;
        this.validator =validator;
    }
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author getById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(()->new ResourceNotFoundException("ENTITY,authorId"));
    }

    @Override
    public Author create(Author author) {
        Set<ConstraintViolation<Author>> violations = validator.validate(author);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        Author authorWithFirstNameAndLastName= authorRepository.findAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        if(authorWithFirstNameAndLastName!=null)
            throw new ResourceValidationException(ENTITY,"An author with the same First Name already exists");

        Author authorWithNickName=authorRepository.findAuthorByNickname(author.getNickname());

        if(authorWithNickName!=null)
            throw new ResourceValidationException(ENTITY,"An author with the same Nickname already exists");

        return authorRepository.save(author);
    }

    @Override
    public Author update(Long authorId, Author request) {

        Set<ConstraintViolation<Author>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

//        Author authorWithFirstNameAndLastName= authorRepository.findAuthorByFirstNameAndLastName(request.getFirstName(), request.getLastName());
//
//        if(authorWithFirstNameAndLastName!=null)
//            throw new ResourceValidationException(ENTITY,"An author with the same First Name already exists");
//        return authorRepository.findById(authorId).map(
//                author -> authorRepository.save(
//                        author.withFirstName(request.getFirstName()))
//                        .withLastName(request.getLastName())
//                        .withNickname(request.getNickname())
//                        .withPhotoUrl(request.getPhotoUrl())).orElseThrow(()->new ResourceNotFoundException("ENTITY,authorId"));

        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("This Author does not exist"));

        // Verificar si ya existe un autor con el mismo nombre y apellido
        Author authorWithFirstNameAndLastName = authorRepository.findAuthorByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        if (authorWithFirstNameAndLastName != null && !authorWithFirstNameAndLastName.getId().equals(authorId)) {
            throw new ResourceValidationException(ENTITY, "An author with the same First Name and Last Name already exists");
        }

        existingAuthor = existingAuthor.withFirstName(request.getFirstName())
                .withLastName(request.getLastName())
                .withNickname(request.getNickname())
                .withPhotoUrl(request.getPhotoUrl());

        Author updatedAuthor = authorRepository.save(existingAuthor);

        return updatedAuthor;
    }

    @Override
    public ResponseEntity<?> delete(Long authorId) {
        return authorRepository.findById(authorId).map(author -> {
            authorRepository.delete(author);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("ENTITY,authorId"));
    }
}
