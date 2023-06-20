package com.example.wikiart.application.author.api;

import com.example.wikiart.application.author.domain.service.AuthorService;
import com.example.wikiart.application.author.mapping.AuthorMapper;
import com.example.wikiart.application.author.resource.AuthorResource;
import com.example.wikiart.application.author.resource.CreateAuthorResource;
import com.example.wikiart.application.author.resource.UpdateAuthorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//RestController-> this annotation indicates that the class is a REST API controller. It provides the necessary functionality to receive and respond to http requests coming to the API
@CrossOrigin("*")
@Tag(name="Author",description="Create, read, update and delete Authors")
@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper mapper;

    public AuthorController(AuthorService authorService, AuthorMapper mapper){
        this.authorService=authorService;
        this.mapper=mapper;
    }

    //GET ALL
    @Operation(summary = "Get all Authors",description = "Get all Authors in database")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Authors found",
            content = {@Content(mediaType="application/json",
            schema=@Schema(implementation= AuthorResource.class))})
    })
    @GetMapping
    public Page<AuthorResource> getAllAuthors(Pageable pageable){
        return mapper.modelListPage(authorService.getAll(),pageable);
    }
    //GET BY ID
    @Operation(summary = "Get an specific author",description = "Get an specific author in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Author Found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = AuthorResource.class))})
    })
    @GetMapping("{authorId}")
    public AuthorResource getAuthorById(@PathVariable Long authorId){
        return mapper.toResource(authorService.getById(authorId));
    }

    //POST
    @Operation(summary = "Create an Author", description = "Create an Author in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorResource.class)
                    ))
    })
    @PostMapping
    public AuthorResource createAuthor(@RequestBody CreateAuthorResource resource){
        return mapper.toResource(authorService.create(mapper.toModel(resource)));
    }

    //PUT UPDATE
    @Operation(summary = "Update an Agency", description = "Update an Agency in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorResource.class)
                    ))
    })
    @PutMapping("/{authorId}")
    public AuthorResource updateAgency(@PathVariable Long authorId, @RequestBody UpdateAuthorResource resource) {
        return mapper.toResource(authorService.update(authorId, mapper.toModel(resource)));
    }

    //DELETE
    @Operation(summary = "Delete an Agency", description = "Delete an Agency from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agency deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{authorId}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long authorId) {
        return authorService.delete(authorId);
    }

}
