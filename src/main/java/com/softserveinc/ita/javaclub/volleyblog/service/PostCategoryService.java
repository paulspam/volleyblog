package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.PostCategory;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_POST_CATEGORIES;

public interface PostCategoryService {
    List<PostCategory> findAll();

    PostCategory findById(int id);

    @PreAuthorize(MANAGE_POST_CATEGORIES)
    PostCategory save(PostCategory postCategory);

    @PreAuthorize(MANAGE_POST_CATEGORIES)
    PostCategory update(PostCategory postCategory);

    @PreAuthorize(MANAGE_POST_CATEGORIES)
    void deleteById(int id);
}
