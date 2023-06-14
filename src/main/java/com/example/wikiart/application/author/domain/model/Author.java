package com.example.wikiart.application.author.domain.model;

import com.example.wikiart.shared.domain.model.AuditModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authors")
public class Author extends AuditModel {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String photoUrl;



}
