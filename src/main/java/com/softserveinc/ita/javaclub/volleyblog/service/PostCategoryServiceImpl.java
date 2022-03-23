package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.PostCategory;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostCategoryRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostCategoryServiceImpl implements PostCategoryService {
    private final PostCategoryRepository postCategoryRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostCategoryServiceImpl(PostCategoryRepository postCategoryRepository, PostRepository postRepository, TagRepository tagRepository) {
        this.postCategoryRepository = postCategoryRepository;
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<PostCategory> findAll;

    @Override
    public List<PostCategory> findAll() {
        return postCategoryRepository.findAll();
    }

    @Override
    public PostCategory findById(int id) {
        return postCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public PostCategory save(PostCategory postCategory) {
        return postCategoryRepository.save(postCategory);
    }

    @Override
    public PostCategory update(PostCategory postCategory) {
        return postCategoryRepository.save(postCategory);
    }

    @Override
    public void deleteById(int id) {
        postCategoryRepository.deleteById(id);

    }

    @Override
    public List<Post> findAllPostsByPostCategory(int id) {
        PostCategory postCategory = postCategoryRepository.getById(id);
        if (postCategory == null) {
            log.info("IN PostCategoryServiceImpl.findAllPostsByPostCategory - postCategory with id {} does not exist", id);
            return null;
        }
        List<Post> allPostsByPostCategory = postRepository.findAllByPostCategory(postCategory);
        return allPostsByPostCategory;
    }


}
