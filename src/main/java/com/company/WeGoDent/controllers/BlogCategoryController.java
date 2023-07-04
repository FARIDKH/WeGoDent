package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.BlogCategoryDTO;
import com.company.WeGoDent.dto.BlogPostDTO;
import com.company.WeGoDent.entity.BlogCategory;
import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.mapper.BlogCategoryMapper;
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

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;


    @GetMapping("/{blogCategoryId}")
    private ResponseEntity<BlogCategoryDTO> getBlogCategoryByID(@PathVariable Long blogCategoryId){
        BlogCategory blogCategory = blogCategoryService.getBlogCategoryById(blogCategoryId);
        BlogCategoryDTO blogCategoryDTO = blogCategoryMapper.toDto(blogCategory);

        return ResponseEntity.ok(blogCategoryDTO);

    }

    @GetMapping
    private ResponseEntity<List<BlogCategoryDTO>> getAllCategories(){
        List<BlogCategory> blogCategory = blogCategoryService.getAllBlogCategories();
        List<BlogCategoryDTO> blogCategoryDTO = blogCategoryMapper.toDto(blogCategory);

        return ResponseEntity.ok(blogCategoryDTO);

    }

    @GetMapping("/{blogCategoryId}/posts")
    private ResponseEntity<List<BlogPostDTO>> getBlogPostByCategory(@PathVariable Long blogCategoryId){
        List<BlogPost> blogPostList = blogCategoryService.getAllPostByCategory(blogCategoryId);
        List<BlogPostDTO> blogPostDTOList = blogMapper.toDto(blogPostList);
        return ResponseEntity.ok(blogPostDTOList);
    }

    @PostMapping
    private ResponseEntity<BlogCategoryDTO> createBlogCategory(@Valid @RequestBody BlogCategoryDTO blogCategoryDTO){
        BlogCategory blogCategory = blogCategoryMapper.toEntity(blogCategoryDTO);
        BlogCategory createdBC = blogCategoryService.createBlogCategory(blogCategory);
        BlogCategoryDTO createdDTO = blogCategoryMapper.toDto(createdBC);

        return ResponseEntity.ok(createdDTO);
    }

    @PutMapping("/{blogCategoryId}")
    private ResponseEntity<BlogCategoryDTO> updateBlogCategory(
            @PathVariable Long blogCategoryId
            , @Valid @RequestBody BlogCategoryDTO blogCategoryDTO){
        BlogCategory blogCategory = blogCategoryMapper.toEntity(blogCategoryDTO);
        BlogCategory createdBC = blogCategoryService.updateBlogCategory(blogCategoryId,blogCategory);
        BlogCategoryDTO createdDTO = blogCategoryMapper.toDto(createdBC);

        return ResponseEntity.ok(createdDTO);
    }

    @DeleteMapping("/{blogCategoryId}")
    private ResponseEntity<Boolean> deleteBlogCategory(
            @PathVariable Long blogCategoryId){
        Boolean value = blogCategoryService.deleteBlogCategory(blogCategoryId);

        return ResponseEntity.ok(value);
    }




}
