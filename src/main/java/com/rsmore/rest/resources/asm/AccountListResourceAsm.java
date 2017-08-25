package com.rsmore.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.rsmore.core.services.util.AccountList;
import com.rsmore.rest.resources.AccountListResource;
import com.rsmore.rest.resources.AccountResource;
import com.rsmore.rest.web.AccountController;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
