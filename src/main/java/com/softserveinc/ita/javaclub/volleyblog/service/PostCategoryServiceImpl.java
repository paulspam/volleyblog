package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.PostCategory;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryServiceImpl implements PostCategoryService {
    private final PostCategoryRepository postCategoryRepository;

    public PostCategoryServiceImpl(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
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


}
