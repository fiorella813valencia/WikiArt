package com.example.wikiart.application.author.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResource {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String photoUrl;


}
