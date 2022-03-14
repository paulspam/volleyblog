package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAllByUser(User user) {
        List<Post> allByUser = postRepository.findAllByUser(user);
        return allByUser;
    }


}
