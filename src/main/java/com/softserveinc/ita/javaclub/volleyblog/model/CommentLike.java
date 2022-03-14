package com.softserveinc.ita.javaclub.volleyblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comment_likes")
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Integer likeId;

    @Column(name = "like_value")
    private Short likeValue;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
