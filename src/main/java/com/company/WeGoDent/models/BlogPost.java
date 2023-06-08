package com.company.WeGoDent.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User author;

    @ManyToMany
    private Set<BlogCategory> categories;

    private String title;
    private String content;
    private Date postedAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
