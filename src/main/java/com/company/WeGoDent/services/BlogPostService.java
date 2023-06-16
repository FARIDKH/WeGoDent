package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AccountService accountService;

    public BlogPost createBlogPost(BlogPost blogPost){
        blogPost.setPostedAt(new Date(System.currentTimeMillis()));
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> getAllPostByCategory(Long id){

        return blogPostRepository.findByCategoryId(id);

    }

    public List<BlogPost> getAll(){

        return blogPostRepository.findAll();

    }


    public BlogPost getById(Long id){
        if(blogPostRepository.existsById(id)){
            return blogPostRepository.getReferenceById(id);
        }
        return null;
    }

    public BlogPost updateBlogPost(Long blogPostId, BlogPost blogPost){
        if(blogPostRepository.existsById(blogPostId)){
            BlogPost bp = blogPostRepository.getReferenceById(blogPostId);
            bp.setCategories(blogPost.getCategories());
            bp.setTitle(blogPost.getTitle());
            bp.setAuthor(blogPost.getAuthor());
            bp.setContent(blogPost.getContent());


            return blogPostRepository.save(bp);

        }
        return null;
    }

    public boolean deleteById(Long blogPostId){
        if(blogPostRepository.existsById(blogPostId)){
            blogPostRepository.deleteById(blogPostId);
            return true;
        }
        return false;
    }



}
