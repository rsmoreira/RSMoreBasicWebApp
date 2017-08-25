package com.rsmore.core.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rsmore.core.model.entities.Account;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountDaoTest {

	@Autowired
	private AccountDAO accountDAO;

	private Account account;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setup() {
		account = new Account();
		account.setName("Name");
		account.setPassword("Password");
		accountDAO.createAccount(account);
	}

	@Test
	@Transactional
	public void testFind() {
		assertNotNull(accountDAO.findAccount(account.getId()));
		assertEquals(account.getName(), "Name");
        assertEquals(account.getPassword(), "Password");
	}
}
