package com.dxc.accountsReceivable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.accountsReceivable.model.AccountReceivable;

@Repository
public interface AccountReceivableRepository extends JpaRepository<AccountReceivable, Long> {

}
