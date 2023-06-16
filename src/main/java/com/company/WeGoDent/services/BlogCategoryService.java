package com.company.WeGoDent.services;


import com.company.WeGoDent.entity.BlogCategory;
import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.repositories.BlogCategoryRepository;
import com.company.WeGoDent.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryService {

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Autowired
    private BlogPostService blogPostService;


    public List<BlogCategory> getAllBlogCategories(){
        return blogCategoryRepository.findAll();
    }

    public List<BlogPost> getAllPostByCategory(Long id){

        return blogPostService.getAllPostByCategory(id);

    }


    public BlogCategory getBlogCategoryById(Long id){
        if (blogCategoryRepository.existsById(id)){
            BlogCategory bc = blogCategoryRepository.getReferenceById(id);
            return bc;
        }
        return null;
    }



    public BlogCategory createBlogCategory(BlogCategory blogCategory){
        return blogCategoryRepository.save(blogCategory);
    }



    public BlogCategory updateBlogCategory(Long id, BlogCategory blogCategory){
        if (blogCategoryRepository.existsById(id)){
            BlogCategory bc = blogCategoryRepository.getReferenceById(id);
            bc.setName(blogCategory.getName());

            blogCategoryRepository.save(bc);
        }
        return null;
    }

    public boolean deleteBlogCategory(Long id){
        if (blogCategoryRepository.existsById(id)){

            blogCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
