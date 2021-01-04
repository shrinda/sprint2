package com.cg.apps.tatasky.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static  com.cg.apps.tatasky.util.AppConstants.USER_NOT_FOUND_CONST;
import static  com.cg.apps.tatasky.util.AppConstants.OPERATION_FAILED_CONST;
import static  com.cg.apps.tatasky.util.AppConstants.WRONG_USER_PASSWORD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.apps.tatasky.controller.UserController;
import com.cg.apps.tatasky.entities.User;
import com.cg.apps.tatasky.exceptions.OperationFailedException;
import com.cg.apps.tatasky.exceptions.ResourceNotFoundException;
import com.cg.apps.tatasky.exceptions.UserAlreadyExistException;

import com.cg.apps.tatasky.payload.LoginReqPayLoad;
import com.cg.apps.tatasky.repository.UserRepository;

/*
 * 
 * @author Vrinda
 * 
 * */



@Service  //@Service annotation is used to mark the class as a service provider
public class UserServiceImpl  implements UserService{
//UserServiceImpl should implement all the methods present in UserService Interface
	
	private static Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired   //To get a relation with User repository
	private UserRepository userRepository;
	
	
	
	
	// ****************************************//
			/*
			 * Function Name: register(User user) Input Parameters:
			 * (User user) ReturnType: 
			 * Description: register user  
			 */

	@Transactional
	@Override
	public User register(User user)  {
		// TODO Auto-generated method stub
		
		logger.info("Enter UserServiceImpl :: method=register");
		
		
Optional<User> check = userRepository.findById(user.getId());
if(check.isPresent())
{throw new UserAlreadyExistException("User Already Exist!" );
	}

		User userObj = null;
	try {
		userObj = userRepository.save(user); //save is used to insert the records in the table
		
	}catch(Exception e)
	{
		throw new OperationFailedException("Couldn't save user."+ e.getMessage());
	}
		
	logger.info("Exit UserServiceImpl :: method=register");
	
		return userObj ;
	}
	
	

	// ****************************************//
			/*
			 * Function Name: update(long id ,User user) Input Parameters:
			 * (User user) ReturnType: 
			 * Description: update user details
			 */
	

	@Transactional
    @Override
	public User update(long id,User user) {
		// TODO Auto-generated method stub
		
		logger.info("Enter UserServiceImpl :: method=update");
		
		
		Optional<User> userObj = null;
		User updateUser = null;
		userObj = userRepository.findById(id); //findById is used to find the particular record using id
		if(!userObj.isPresent())
		{
			throw new ResourceNotFoundException(USER_NOT_FOUND_CONST +user);  //throws exception if  user id does not match the database records
			
		}
		else
		{
			userObj.get().setFirstName(user.getFirstName());
			userObj.get().setLastName(user.getLastName());
			
			try
			{
				updateUser = userRepository.save(userObj.get());
			}catch(Exception e)
			{
				throw new OperationFailedException(OPERATION_FAILED_CONST +e.getMessage());
			}
		}
		
		
		logger.info("Exit UserServiceImpl :: method=update");
		return updateUser;
	}

	
	

	// ****************************************//
			/*
			 * Function Name:  findById(Long id) Input Parameters:
			 * (User user) ReturnType: 
			 * Description: fetch  user details  by userId  
			 */
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		
		logger.info("Enter UserServiceImpl :: method=findById");
		
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
		throw new ResourceNotFoundException(USER_NOT_FOUND_CONST+ id);
		
	
		logger.info("Exit UserServiceImpl :: method=findById");
		
		return user.get();
	}
	
	

	// ****************************************//
			/*
			 * Function Name: deleteByUserId(Long userId) Input Parameters:
			 * (User user) ReturnType: 
			 * Description:  delete user by user Id  
			 */
	
	
	
	@Override
	public User deleteByUserId(Long userid) {
		// TODO Auto-generated method stub
	
		logger.info("Enter UserServiceImpl :: method=deleteByUserId");
		
		
		Optional<User> userObj  = userRepository.findById(userid);
		if(!userObj.isPresent())
		{
			throw new ResourceNotFoundException(USER_NOT_FOUND_CONST + userid);
		}
		else
		{
		try
		{
			userRepository.delete(userObj.get()); //deleteById() is a predefined method present in JPARepository
		}catch(Exception e)
		{
			throw new OperationFailedException(OPERATION_FAILED_CONST +e.getMessage());
			
		}
		
		}
		
		logger.info("Exit UserServiceImpl :: method=deleteByUserId");	
		
return userObj.get();		
	}


	
	

	// ****************************************//
			/*
			 * Function Name: sinIn(LoginReqPayLoad loginUser) Input Parameters:
			 * (String res) ReturnType: 
			 * Description:  sign In user by userId and Password 
			 */


//@Override
//public String signIn(LoginReqPayLoad loginduser) {
//	// TODO Auto-generated method stub
//	
//	
//	logger.info("Enter UserServiceImpl :: method=signIn");
//	
//	if(loginduser == null)
//	{
//		throw new ResourceNotFoundException("User Not Found!");
//	}
//	
//	String loginUser = "User Logged In Successfully!";
//	Optional<User> checkUser = userRepository.findById(loginduser.getUserId());
//	if(!checkUser.isPresent())
//	{
//		
//	throw new ResourceNotFoundException(USER_NOT_FOUND_CONST +loginduser.getUserId());	
//		
//	}
//	else
//	{
//	if(!checkUser.get().getPassword().equals(loginduser.getPassword()))
//			{
//		throw new ResourceNotFoundException(WRONG_USER_PASSWORD);
//		
//			}
//			
//	}
//	
//	
//	logger.info("Exit UserServiceImpl :: method=signIn");
//	return loginUser;	
//}


	@Override
	public String signIn(LoginReqPayLoad logindUser)
	{
		logger.info("Enter UserServiceImpl :: method = signIn");
		String str;
		if(logindUser ==null)
		{ throw new ResourceNotFoundException("User Not Found!");
		}
		Optional<User> userObj = userRepository.findById(logindUser.getUserId());
		if(!userObj.isPresent())
		{
			throw new ResourceNotFoundException(USER_NOT_FOUND_CONST +logindUser.getUserId());
		}
		else
		{
			String pwd = userObj.get().getPassword() ;
			if(!pwd.equals(logindUser.getPassword()))
			{
				throw new ResourceNotFoundException(WRONG_USER_PASSWORD);
				
				
			}
			try
			{
				str = "Sign in successfull";
				userRepository.saveAndFlush(userObj.get());
				
			}catch(Exception e)
	
			{
				throw new OperationFailedException(OPERATION_FAILED_CONST);
			}
			
		}
		return str;
			
		}	
	
	


// ****************************************//
		/*
		 * Function Name: resetPassword(LoginReqPayLoad loginUser, String newPass) Input Parameters:
		 * (String reset) ReturnType: 
		 * Description:  resetting password  
		 */


@Override
public String resetPassword(LoginReqPayLoad loginuser, String newpass) {
	// TODO Auto-generated method stub
	
	logger.info("Enter UserServiceImpl :: method=resetPassword");
	
	
	String reset = "Password Changed Successfully!";
	User res = null;
	Optional<User> userObj = userRepository.findById(loginuser.getUserId());
	
	if(!userObj.isPresent())
	{
		throw new ResourceNotFoundException(USER_NOT_FOUND_CONST+loginuser.getUserId());
		
	}
	
	else
	{String password = userObj.get().getPassword();
	if(!password.equals(loginuser.getPassword()))
	{
		throw new ResourceNotFoundException(WRONG_USER_PASSWORD);
		
	}
	
	try {
		
		userObj.get().setPassword(newpass);
		res= userRepository.saveAndFlush(userObj.get());
		
		
		
	} catch(Exception e)
	{
		throw new OperationFailedException(OPERATION_FAILED_CONST);
	}
	
	
	
	}
	
	
	logger.info("Exit UserServiceImpl :: method=resetPassword");	
	
	return reset;
}
















	
	
	
	
	
}
