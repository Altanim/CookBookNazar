package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.services.IngredientFilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Файлы", description = "скачать и импортировать ингредиенты")
@RequestMapping("/ingredientFiles")
public class IngredientFilesController {
    private final IngredientFilesService ingredientFilesService;

    public IngredientFilesController(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }
    @Operation(summary = "Скачивание ингредиентов", description = "Скачивание ингредиентов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Скачивание успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка"),
    })
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
       File ingFile = ingredientFilesService.getDataFile();
       if (ingFile.exists()){
           InputStreamResource resource = new InputStreamResource(new FileInputStream(ingFile));
           return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_JSON)
                   .contentLength(ingFile.length())
                   .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"ingredients.json\"")
                   .body(resource);
       } else {
           return ResponseEntity.noContent().build();
       }
    }
    @Operation(summary = "Импорт", description = "Импорт рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка"),
    })
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile ingFile) {
        ingredientFilesService.cleanDataFileIngredient();
        File ingFile1 = ingredientFilesService.getDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(ingFile1)){
            IOUtils.copy(ingFile.getInputStream(), fileOutputStream);
            return   ResponseEntity.ok().build();
        }    catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
