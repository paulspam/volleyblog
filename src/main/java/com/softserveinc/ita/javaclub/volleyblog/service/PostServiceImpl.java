package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.PostDto;
import com.softserveinc.ita.javaclub.volleyblog.model.Comment;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.Tag;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.CommentRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final MappingUtils mappingUtils;

    private final TagRepository tagRepository;

    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository, MappingUtils mappingUtils, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.mappingUtils = mappingUtils;
        this.tagRepository = tagRepository;
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByUser(User user) {
        List<Post> allByUser = postRepository.findAllByUser(user);
        return allByUser;
    }

    @Override
    public PostDto getPostWithComments(Integer postId) {
        Post post = findById(postId);
        List<Comment> comments = commentRepository.findAllByPost(post);
        List<Tag> tags = tagRepository.findAllByPosts(post);
        PostDto postDto = mappingUtils.mapToPostDto(post);
        postDto.setComments(comments);
        postDto.setTags(tags);

        return  postDto;
    }


}
