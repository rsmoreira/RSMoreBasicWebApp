package com.rsmore.rest.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rsmore.core.model.entities.Account;
import com.rsmore.core.model.entities.Blog;
import com.rsmore.core.services.AccountService;
import com.rsmore.core.services.exceptions.AccountDoesNotExistException;
import com.rsmore.core.services.exceptions.AccountExistsException;
import com.rsmore.core.services.exceptions.BlogExistsException;
import com.rsmore.core.services.util.AccountList;
import com.rsmore.core.services.util.BlogList;
import com.rsmore.rest.exceptions.BadRequestException;
import com.rsmore.rest.exceptions.ConflictException;
import com.rsmore.rest.exceptions.NotFoundException;
import com.rsmore.rest.resources.AccountListResource;
import com.rsmore.rest.resources.AccountResource;
import com.rsmore.rest.resources.BlogListResource;
import com.rsmore.rest.resources.BlogResource;
import com.rsmore.rest.resources.asm.AccountListResourceAsm;
import com.rsmore.rest.resources.asm.AccountResourceAsm;
import com.rsmore.rest.resources.asm.BlogListResourceAsm;
import com.rsmore.rest.resources.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
	private AccountService accountService;

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccountListResource> findAllAccounts(
			@RequestParam(value = "name", required = false) String name) {
		AccountList list = null;
		if (name == null) {
			list = accountService.findAllAccounts();
		} else {
			Account account = accountService.findByAccountName(name);
			if (account == null) {
				list = new AccountList(new ArrayList<Account>());
			} else {
				list = new AccountList(Arrays.asList(account));
			}
		}
		AccountListResource res = new AccountListResourceAsm().toResource(list);
		return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(
			@RequestBody AccountResource sentAccount) {
		try {
			Account createdAccount = accountService.createAccount(sentAccount
					.toAccount());
			AccountResource res = new AccountResourceAsm()
					.toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers,
					HttpStatus.CREATED);
		} catch (AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(
			@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(
			@PathVariable Long accountId, @RequestBody BlogResource res) {
		try {
			Blog createdBlog = accountService.createBlog(accountId,
					res.toBlog());
			BlogResource createdBlogRes = new BlogResourceAsm()
					.toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogRes.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogResource>(createdBlogRes, headers,
					HttpStatus.CREATED);
		} catch (AccountDoesNotExistException exception) {
			throw new BadRequestException(exception);
		} catch (BlogExistsException exception) { // Added because
													// AccountControllerTest.createBlogExistingBlogName
													// that mock an behavior to
													// always throw a
													// BlogExistingException.
													// Tests is saying like:
													// "take a look there, you
													// need to treat this
													// BlogExistingException.
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs(
			@PathVariable Long accountId) {
		try {
			BlogList blogList = accountService.findBlogsByAccount(accountId);
			BlogListResource blogListRes = new BlogListResourceAsm()
					.toResource(blogList);
			return new ResponseEntity<BlogListResource>(blogListRes,
					HttpStatus.OK);
		} catch (AccountDoesNotExistException exception) {
			throw new NotFoundException(exception);
		}
	}

}
