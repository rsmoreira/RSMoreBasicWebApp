package com.rsmore.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rsmore.core.services.util.BlogEntryList;
import com.rsmore.rest.resources.BlogEntryListResource;
import com.rsmore.rest.resources.BlogEntryResource;
import com.rsmore.rest.web.BlogController;

public class BlogEntryListResourceAsm extends
		ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {
	public BlogEntryListResourceAsm() {
		super(BlogController.class, BlogEntryListResource.class);
	}

	@Override
	public BlogEntryListResource toResource(BlogEntryList list) {
		List<BlogEntryResource> resources = new BlogEntryResourceAsm()
				.toResources(list.getEntries());
		BlogEntryListResource listResource = new BlogEntryListResource();
		listResource.setEntries(resources);
		listResource.add(linkTo(
				methodOn(BlogController.class).findAllBlogEntries(
						list.getBlogId())).withSelfRel());
		return listResource;
	}
}
