package com.example.wikiart.application.author.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorResource {
    //va lo que se va a poder manipular
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
