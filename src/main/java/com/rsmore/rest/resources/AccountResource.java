package com.rsmore.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsmore.core.model.entities.Account;

public class AccountResource extends ResourceSupport {
    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore // ignore serialization on json
    public String getPassword() {
        return password;
    }

    @JsonProperty // ignore serialization on json
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }
}
