package com.example.wikiart.application.author.domain.persistence;

import com.example.wikiart.application.author.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//by extending this interface, the AuthorRepository inherits methods and functionality
// for accessing and manipulating data in the database related to the Author entity.
@Repository //indicates that this interface is a Spring repository and is responsible for providing access and manipulation of data related to the Author entity.
public interface AuthorRepository extends JpaRepository<Author,Long> {
    //<Author, Long>: These are the type parameters used in the JpaRepository
    //(Author) indicates the type of entity with which the repository is associated
    //(Long) indicates the data type of the Author entity identifier, in this case "id" is long
    List<Author> findAll();
    List<Author> findAuthorsByFirstName(String name);
    Author findAuthorByFirstName(String name);
    Author findAuthorByFirstNameAndLastName(String firstName,String lastName);

    Author findAuthorByNickname(String nickName);


}
