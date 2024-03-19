package com.payable.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payable.model.AccountPayable;
@Repository
public interface AccountPayableDao extends JpaRepository<AccountPayable, Long> {

}
