package com.rsmore.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.rsmore.core.model.entities.BlogEntry;

/**
 * 
 * This is your class like a DTO, used to does send your JPA entity (BlogEntry)
 * to the view layer.
 * 
 *
 */
public class BlogEntryResource extends ResourceSupport {
	private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogEntry toBlogEntry() {
        BlogEntry entry = new BlogEntry();
        entry.setTitle(title);
        entry.setContent(content);
        return entry;
    }

}
