package com.example.wikiart.application.author.api;

import com.example.wikiart.application.author.domain.service.ProviderService;
import com.example.wikiart.application.author.mapping.ProviderMapper;
import com.example.wikiart.application.author.resource.CreateProviderResource;
import com.example.wikiart.application.author.resource.ProviderResource;
import com.example.wikiart.application.author.resource.UpdateProviderResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name="Provider", description = "Create,read, update and delete Providers")
@RestController
@RequestMapping("api/v1/providers")
public class ProviderController {

    private final ProviderService providerService;
    private final ProviderMapper mapper;
    public ProviderController(ProviderService providerService,ProviderMapper mapper){
        this.providerService=providerService;
        this.mapper=mapper;
    }
    //GET ALL PAGES
    @Operation(summary = "Get all Providers",description = "Get all providers in database")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Providers found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ProviderResource.class))})
    })
    @GetMapping()
    public Page<ProviderResource> getAllProviders(Pageable pageable){
        return mapper.modelListPage(providerService.getAll(),pageable);
    }
    //GET BY ID
    @Operation(summary = "Get an specific Provider",description = "Get a provider")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Provider found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ProviderResource.class))})
    })
    @GetMapping("/{providerId}")
    public ProviderResource getProviderById(@PathVariable Long providerId){
        return mapper.toResource(providerService.getById(providerId));
    }

    //POST
    @Operation(summary = "Create a provider",description = "Create a provider in database")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Provider created",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ProviderResource.class)))
    })
    @PostMapping
    public ProviderResource createProvider(@RequestBody CreateProviderResource resource){
        return mapper.toResource(providerService.create(mapper.toModel(resource)));
    }

    //UPDATE
    @Operation(summary = "Update info for an Provider", description = "Update Provider info in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Provider updated",
            content = @Content(mediaType = "application/json",
            schema=@Schema(implementation = ProviderResource.class)))

    })
    @PutMapping("/{providerId}")
    public ProviderResource updateProvider(@PathVariable Long providerId, @RequestBody UpdateProviderResource resource){
        return mapper.toResource(providerService.update(providerId,mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a Provider",description = "Delete a provider in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider deleted",
            content = @Content(mediaType = "200",
            schema = @Schema(implementation = ProviderResource.class)))
    })
    @DeleteMapping("/{providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long providerId){
        return providerService.delete(providerId);
    }


}
