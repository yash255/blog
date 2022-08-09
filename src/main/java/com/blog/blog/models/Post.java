package com.blog.blog.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    
    @NotEmpty
    @Size(min = 1)
    @Column(nullable = false)
    private String postTitle;
    
    @NotEmpty
    @Column(length = 10000,nullable = false)
    private String content;

    private String imageName;

    private Date addedDate;
    
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();
}
