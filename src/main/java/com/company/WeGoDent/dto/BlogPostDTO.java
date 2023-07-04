package com.company.WeGoDent.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class BlogPostDTO {

    private Long id;


    private String title;
    private String content;
    private List<BlogCategoryDTO> blogCategoryDTOS;
    private Long authorId;


}
