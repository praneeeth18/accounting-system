package com.dxc.accountreceivable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.accountreceivable.model.AccountReceivable;

@Repository
public interface AccountReceivableRepository extends JpaRepository<AccountReceivable, Long> {

	List<AccountReceivable> findByCompanyId(int companyId);
}
