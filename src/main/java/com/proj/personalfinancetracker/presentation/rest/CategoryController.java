package com.proj.personalfinancetracker.presentation.rest;

import com.proj.personalfinancetracker.model.category.CategoryListModel;
import com.proj.personalfinancetracker.model.category.CategoryRequestModel;
import com.proj.personalfinancetracker.model.category.CategoryResponseModel;
import com.proj.personalfinancetracker.presentation.rest.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Manage category names and their type")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get All Categories", description = "Returns all active categories")
    public ResponseEntity<CategoryListModel> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<CategoryResponseModel> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    //@Operation(summary = "Create new Task")
    @PostMapping
    @Operation(summary = "Create category")
    public ResponseEntity<CategoryResponseModel> create(@Valid @RequestBody CategoryRequestModel request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category")
    public ResponseEntity<CategoryResponseModel> update(@PathVariable Long id,
                                                        @Valid @RequestBody CategoryRequestModel request){
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft Delete a category")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
