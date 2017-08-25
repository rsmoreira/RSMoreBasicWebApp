package com.rsmore.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsmore.core.model.entities.BlogEntry;
import com.rsmore.core.repositories.BlogEntryDAO;
import com.rsmore.core.services.BlogEntryService;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

	@Autowired
    private BlogEntryDAO entryDAO;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return entryDAO.findBlogEntry(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return entryDAO.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        return entryDAO.updateBlogEntry(id, data);
    }
}
