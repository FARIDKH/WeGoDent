package com.company.WeGoDent.repositories;

import com.company.WeGoDent.models.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long>  {

}
