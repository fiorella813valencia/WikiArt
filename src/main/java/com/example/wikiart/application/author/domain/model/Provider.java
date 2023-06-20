package com.example.wikiart.application.author.domain.model;

import com.example.wikiart.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="provider")
public class Provider extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String apiUrl;
    private Boolean keyRequired=false;
    @NotBlank
    @NotNull
    private String apiKey;

}
