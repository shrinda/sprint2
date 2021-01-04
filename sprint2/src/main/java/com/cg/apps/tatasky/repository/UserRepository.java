package com.cg.apps.tatasky.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.cg.apps.tatasky.entities.User;

/*
 * 
 * @author Vrinda
 * 
 * */


public interface UserRepository extends JpaRepository<User,Long> //All the methods present in JPARepository can be used by UserRepository

{

	
}
