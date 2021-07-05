package br.com.cvc.hotel.controllers;

import br.com.cvc.hotel.adapters.BackendHotelAdapter;
import br.com.cvc.hotel.exceptions.ObjectNotFoundException;
import br.com.cvc.hotel.exceptions.WrongParameterException;
import br.com.cvc.hotel.models.dtos.HotelDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/hotels")
public class HotelDetail {

    @Autowired
    private BackendHotelAdapter backendHotelAdapter;

    @Operation(summary = "Get hotel details by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels details founded.",
                         content = { @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameter supplied.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hotels not found with this id.", content = @Content),
            @ApiResponse(responseCode = "502", description = "Remote server dependency could not respond.", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> details(@PathVariable("id") Long id) throws Throwable {
        if (id == null) {
            throw new WrongParameterException("Hotel id cannot be null.");
        }
        CompletableFuture<List<HotelDto>> detailedHotels = backendHotelAdapter.getDetails(id);
        return ResponseEntity.ok(detailedHotels.get().stream().findFirst().orElseThrow(
                () -> new ObjectNotFoundException("No hotel field for this id.")));
    }
}
