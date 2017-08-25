package com.rsmore.core.repositories;

import java.util.List;

import com.rsmore.core.model.entities.Blog;

public interface BlogDAO {
    public Blog createBlog(Blog data);
    public List<Blog> findAllBlogs();
    public Blog findBlog(Long id);
    public Blog findBlogByTitle(String title);
    public List<Blog> findBlogsByAccount(Long accountId);
}
