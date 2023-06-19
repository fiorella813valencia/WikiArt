package com.example.wikiart.application.author.domain.model;

import com.example.wikiart.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.transform.Source;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="author")
//extends means Author inherits from AuditModel
public class Author extends AuditModel implements Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String nickname;

    private String photoUrl;




}
