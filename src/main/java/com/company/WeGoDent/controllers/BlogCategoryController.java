package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.BlogCategoryDTO;
import com.company.WeGoDent.dto.BlogPostDTO;
import com.company.WeGoDent.entity.BlogCategory;
import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.mapper.BlogMapper;
import com.company.WeGoDent.services.BlogCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blogcategory")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogMapper blogMapper;


    @GetMapping("/{id}")
    private ResponseEntity<BlogCategoryDTO> getBlogCategoryByID(@PathVariable Long id){
        BlogCategory blogCategory = blogCategoryService.getBlogCategoryById(id);
        BlogCategoryDTO blogCategoryDTO = blogMapper.entityToDto(blogCategory);

        return ResponseEntity.ok(blogCategoryDTO);

    }

    @GetMapping
    private ResponseEntity<List<BlogCategoryDTO>> gellAllCategories(){
        List<BlogCategory> blogCategory = blogCategoryService.getAllBlogCategories();
        List<BlogCategoryDTO> blogCategoryDTO = blogMapper.categoryEntitiesToDtos(blogCategory);

        return ResponseEntity.ok(blogCategoryDTO);

    }

    @GetMapping("/{id}/posts")
    private ResponseEntity<List<BlogPostDTO>> getBlogPostByCategory(@PathVariable Long id){
        List<BlogPost> blogPostList = blogCategoryService.getAllPostByCategory(id);
        List<BlogPostDTO> blogPostDTOList = blogMapper.entitiesToDtos(blogPostList);
        return ResponseEntity.ok(blogPostDTOList);
    }

    @PostMapping
    private ResponseEntity<BlogCategoryDTO> createBlogCategory(@Valid @RequestBody BlogCategoryDTO blogCategoryDTO){
        BlogCategory blogCategory = blogMapper.DtoToEntity(blogCategoryDTO);
        BlogCategory createdBC = blogCategoryService.createBlogCategory(blogCategory);
        BlogCategoryDTO createdDTO = blogMapper.entityToDto(createdBC);

        return ResponseEntity.ok(createdDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<BlogCategoryDTO> updateBlogCategory(
            @PathVariable Long id
            , @Valid @RequestBody BlogCategoryDTO blogCategoryDTO){
        BlogCategory blogCategory = blogMapper.DtoToEntity(blogCategoryDTO);
        BlogCategory createdBC = blogCategoryService.updateBlogCategory(id,blogCategory);
        BlogCategoryDTO createdDTO = blogMapper.entityToDto(createdBC);

        return ResponseEntity.ok(createdDTO);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteBlogCategory(
            @PathVariable Long id){
        Boolean value = blogCategoryService.deleteBlogCategory(id);

        return ResponseEntity.ok(value);
    }




}
