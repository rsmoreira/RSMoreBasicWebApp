package com.rsmore.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rsmore.core.services.util.BlogList;
import com.rsmore.rest.resources.BlogListResource;
import com.rsmore.rest.web.BlogController;

public class BlogListResourceAsm extends
		ResourceAssemblerSupport<BlogList, BlogListResource> {

	public BlogListResourceAsm() {
		super(BlogController.class, BlogListResource.class);
	}

	@Override
	public BlogListResource toResource(BlogList blogList) {
		BlogListResource res = new BlogListResource();
		res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
		return res;
	}
}
