package zawkin.me.asuna.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.me.asuna.dto.WaifuDTO;
import zawkin.me.asuna.entity.WaifuEntity;
import zawkin.me.asuna.service.WaifuService;

import java.util.List;

@RestController
@RequestMapping("/")  // Use a more specific URL for clarity
@RequiredArgsConstructor
@Tag(name = "Waifu API", description = "Endpoints to manage waifus")  // Clear tag name
public class WaifuController {

    private final WaifuService waifuService;

    @Operation(
            summary = "Add a new waifu",
            description = "Create a new waifu entry in the database",
            responses = {
                    @ApiResponse(description = "Waifu created successfully", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = WaifuEntity.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400")
            }
    )
    @PostMapping("/")
    public ResponseEntity<WaifuEntity> addWaifu(@RequestBody WaifuDTO waifuDTO) {
        WaifuEntity waifuEntity = waifuService.addWaifu(waifuDTO);
        return ResponseEntity.ok(waifuEntity);
    }

    @Operation(
            summary = "Get all waifus",
            description = "Retrieve a list of all waifus",
            responses = {
                    @ApiResponse(description = "List of waifus", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = WaifuDTO.class)))),
                    @ApiResponse(description = "No waifus found", responseCode = "404")
            }
    )
    @GetMapping("")
    public ResponseEntity<List<WaifuDTO>> getAllWaifus() {
        List<WaifuDTO> waifus = waifuService.getAllWaifus();
        return ResponseEntity.ok(waifus);
    }

    @Operation(
            summary = "Delete a waifu",
            description = "Remove a waifu from the database by its ID",
            responses = {
                    @ApiResponse(description = "Waifu deleted successfully", responseCode = "204"),
                    @ApiResponse(description = "Waifu not found", responseCode = "404")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaifu(@Parameter(description = "ID of the waifu to delete") @PathVariable Long id) {
        waifuService.deleteWaifu(id);
        return ResponseEntity.noContent().build();
    }
}
