package com.company.WeGoDent.controllers;


import com.company.WeGoDent.dto.BlogPostDTO;
import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.mapper.BlogMapper;
import com.company.WeGoDent.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private BlogMapper blogPostMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getById(id);
        return ResponseEntity.ok(blogPostMapper.entityToDto(blogPost));
    }

    @GetMapping
    public ResponseEntity<List<BlogPostDTO>> getAll() {
        List<BlogPost> blogPosts = blogPostService.getAll();
        return ResponseEntity.ok(blogPostMapper.entitiesToDtos(blogPosts));
    }

    @PostMapping
    public ResponseEntity<BlogPostDTO> create(@RequestBody BlogPostDTO blogPostDto) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(blogPostMapper.dtoToEntity(blogPostDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostMapper.entityToDto(createdBlogPost));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> update(@PathVariable Long id, @RequestBody BlogPostDTO blogPostDto) {
        BlogPost updatedBlogPost = blogPostService.updateBlogPost(id, blogPostMapper.dtoToEntity(blogPostDto));
        return ResponseEntity.ok(blogPostMapper.entityToDto(updatedBlogPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogPostService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
