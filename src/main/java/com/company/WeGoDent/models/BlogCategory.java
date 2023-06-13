package com.company.WeGoDent.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "blog_categories")
public class BlogCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<BlogPost> blogPosts;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
