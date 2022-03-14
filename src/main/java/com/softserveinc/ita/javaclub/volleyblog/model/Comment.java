package com.softserveinc.ita.javaclub.volleyblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_created_date")
    private Date commentCreatedDate;

    @Column(name = "comment_modified_date")
    private Date commentModifiedDate;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;
}
