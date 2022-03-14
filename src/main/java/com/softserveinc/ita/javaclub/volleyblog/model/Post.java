package com.softserveinc.ita.javaclub.volleyblog.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "post_title")
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "post_modified_date")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
//
//    @OneToMany(mappedBy = "post")
////    @JoinColumn(name = "post_id")
//    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;

    @ManyToOne
    @JoinColumn(name = "post_status_id")
    private PostStatus postStatus;

}
