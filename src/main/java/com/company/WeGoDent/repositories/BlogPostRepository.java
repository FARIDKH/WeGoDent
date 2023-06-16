package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>  {

    @Query("SELECT bp FROM BlogPost bp JOIN bp.categories c WHERE c.id = :categoryId")
    List<BlogPost> findByCategoryId(@Param("categoryId") Long categoryId);
}
