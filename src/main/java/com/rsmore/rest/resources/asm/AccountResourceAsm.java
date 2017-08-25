package com.rsmore.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rsmore.core.model.entities.Account;
import com.rsmore.rest.resources.AccountResource;
import com.rsmore.rest.web.AccountController;

public class AccountResourceAsm extends
		ResourceAssemblerSupport<Account, AccountResource> {
	public AccountResourceAsm() {
		super(AccountController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource res = new AccountResource();
		res.setName(account.getName());
		res.setPassword(account.getPassword());
		res.add(linkTo(
				methodOn(AccountController.class).getAccount(account.getId()))
				.withSelfRel());
		res.add(linkTo(
				methodOn(AccountController.class).findAllBlogs(account.getId()))
				.withRel("blogs"));
		return res;
	}
}
