package com.example.wikiart.application.author.resource;

import lombok.*;


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
