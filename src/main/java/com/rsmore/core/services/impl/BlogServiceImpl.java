package com.rsmore.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsmore.core.model.entities.Blog;
import com.rsmore.core.model.entities.BlogEntry;
import com.rsmore.core.repositories.BlogDAO;
import com.rsmore.core.repositories.BlogEntryDAO;
import com.rsmore.core.services.BlogService;
import com.rsmore.core.services.exceptions.BlogNotFoundException;
import com.rsmore.core.services.util.BlogEntryList;
import com.rsmore.core.services.util.BlogList;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
    private BlogDAO blogDAO;

	@Autowired
    private BlogEntryDAO entryDAO;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = entryDAO.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogDAO.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogDAO.findBlog(blogId);
        if(blog == null)
        {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, entryDAO.findByBlogId(blogId));
    }

    @Override
    public Blog findBlog(Long id) {
        return blogDAO.findBlog(id);
    }
}
