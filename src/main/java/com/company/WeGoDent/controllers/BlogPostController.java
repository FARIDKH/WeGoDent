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

    @GetMapping("/{blogPostId}")
    public ResponseEntity<BlogPostDTO> getById(@PathVariable Long blogPostId) {
        BlogPost blogPost = blogPostService.getById(blogPostId);
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

    @PutMapping("/{blogPostId}")
    public ResponseEntity<BlogPostDTO> update(@PathVariable Long blogPostId, @RequestBody BlogPostDTO blogPostDto) {
        BlogPost updatedBlogPost = blogPostService.updateBlogPost(blogPostId, blogPostMapper.dtoToEntity(blogPostDto));
        return ResponseEntity.ok(blogPostMapper.entityToDto(updatedBlogPost));
    }

    @DeleteMapping("/{blogPostId}")
    public ResponseEntity<Void> delete(@PathVariable Long blogPostId) {
        blogPostService.deleteById(blogPostId);
        return ResponseEntity.noContent().build();
    }
}
