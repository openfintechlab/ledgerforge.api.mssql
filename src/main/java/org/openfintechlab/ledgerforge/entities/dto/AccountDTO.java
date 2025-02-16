package org.openfintechlab.ledgerforge.entities.dto;

import java.util.List;

import org.openfintechlab.ledgerforge.entities.Account;
import org.openfintechlab.ledgerforge.entities.Metadata;

public class AccountDTO extends Account {
    private Metadata metadata;

    public List<Account> getAll(){
        List<Account> lstAccounts =  Account.listAll();
        return lstAccounts;
    }

}
