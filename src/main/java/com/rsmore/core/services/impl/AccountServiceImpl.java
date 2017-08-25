package com.rsmore.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsmore.core.model.entities.Account;
import com.rsmore.core.model.entities.Blog;
import com.rsmore.core.repositories.AccountDAO;
import com.rsmore.core.repositories.BlogDAO;
import com.rsmore.core.services.AccountService;
import com.rsmore.core.services.exceptions.AccountDoesNotExistException;
import com.rsmore.core.services.exceptions.AccountExistsException;
import com.rsmore.core.services.exceptions.BlogExistsException;
import com.rsmore.core.services.util.AccountList;
import com.rsmore.core.services.util.BlogList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private BlogDAO blogDAO;

	@Override
	public Account findAccount(Long id) {
		return accountDAO.findAccount(id);
	}

	@Override
	public Account createAccount(Account data) {
		Account account = accountDAO.findAccountByName(data.getName());
		if (account != null) {
			throw new AccountExistsException();
		}
		return accountDAO.createAccount(data);
	}

	@Override
	public Blog createBlog(Long accountId, Blog data) {
		Blog blogSameTitle = blogDAO.findBlogByTitle(data.getTitle());

		if (blogSameTitle != null) {
			throw new BlogExistsException();
		}

		Account account = accountDAO.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}

		Blog createdBlog = blogDAO.createBlog(data);

		createdBlog.setOwner(account);

		return createdBlog;
	}

	@Override
	public BlogList findBlogsByAccount(Long accountId) {
		Account account = accountDAO.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}
		return new BlogList(blogDAO.findBlogsByAccount(accountId));
	}

	@Override
	public AccountList findAllAccounts() {
		return new AccountList(accountDAO.findAllAccounts());
	}

	@Override
	public Account findByAccountName(String name) {
		return accountDAO.findAccountByName(name);
	}
}
