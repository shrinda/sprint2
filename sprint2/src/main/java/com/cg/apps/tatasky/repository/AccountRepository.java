package com.cg.apps.tatasky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.apps.tatasky.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

}
