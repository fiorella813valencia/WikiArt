package com.example.wikiart.application.author.domain.service;

import com.example.wikiart.application.author.domain.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    //We got the CRUD here !!
    List<Author> getAll();
    Page<Author> getAll(Pageable pageable);
    Author getById(Long authorId);
    Author create(Author author);
    Author update(Long authorId,Author request);
    //request contains the new data of the author
    ResponseEntity<?> delete(Long authorId);
    //this function returns a ResponseEntity instance, which is an HTTP response indicating whether the deletion was successful

}
