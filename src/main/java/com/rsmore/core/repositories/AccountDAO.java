package com.rsmore.core.repositories;

import java.util.List;

import com.rsmore.core.model.entities.Account;

public interface AccountDAO {

	public List<Account> findAllAccounts();

	public Account findAccount(Long id);

	public Account findAccountByName(String name);

	public Account createAccount(Account data);
}
