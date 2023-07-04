package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.BlogCategoryDTO;
import com.company.WeGoDent.dto.BlogPostDTO;
import com.company.WeGoDent.entity.BlogCategory;
import com.company.WeGoDent.entity.BlogPost;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BlogCategoryMapper.class} )
public interface BlogMapper extends EntityMapper<BlogPostDTO, BlogPost> {







    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "categories", target = "blogCategoryDTOS")

    BlogPostDTO toDto(BlogPost blogPost);


    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "blogCategoryDTOS", target = "categories")
    BlogPost toEntity(BlogPostDTO blogPostDto);



//    List<BlogCategoryDTO> categoryEntitiesToDtos(List<BlogCategory> blogPosts);
//    List<BlogCategory> dtosToCategoryEntities(List<BlogCategoryDTO> blogPostDtos);
//
//
//
//    @AfterMapping
//    default void mapCategoryIdsToEntities(BlogPostDTO blogPostDto, @MappingTarget BlogPost blogPost) {
//        if (blogPostDto.getCategoryIds() != null) {
//            Set<BlogCategory> categories = blogPostDto.getCategoryIds().stream()
//                    .map(id -> {
//                        BlogCategory category = new BlogCategory();
//                        category.setId(id);
//                        return category;
//                    })
//                    .collect(Collectors.toSet());
//            blogPost.setCategories(categories);
//        }
//    }

}
