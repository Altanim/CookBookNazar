package com.nazar.cookbooknazar.controllers;

import com.nazar.cookbooknazar.services.RecipeFilesService;
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
@Tag(name = "Файлы", description = "скачать и импортировать рецепты")
@RequestMapping("/recipeFiles")
public class RecipeFilesController {
    private final RecipeFilesService recipeFilesService;

    public RecipeFilesController(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }
    @Operation(summary = "Скачивание рецептов бесплатно без регистрации", description = "Скачивание рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Скачивание успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка"),
    })
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File recFile = recipeFilesService.getDataFile();
        if (recFile.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(recFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(summary = "Импорт рецептов", description = "Импорт рецептов")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка"),
    })
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile recFile) {
        recipeFilesService.cleanDataFileRecipe();
        File recFile1 = recipeFilesService.getDataFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(recFile1)){
            IOUtils.copy(recFile.getInputStream(), fileOutputStream);
            return   ResponseEntity.ok().build();
        }    catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
