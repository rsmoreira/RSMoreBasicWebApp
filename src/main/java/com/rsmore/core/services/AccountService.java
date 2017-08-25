package com.rsmore.core.services;

import com.rsmore.core.model.entities.Account;
import com.rsmore.core.model.entities.Blog;
import com.rsmore.core.services.util.AccountList;
import com.rsmore.core.services.util.BlogList;

public interface AccountService {
	public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
}
