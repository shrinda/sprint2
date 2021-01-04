package com.cg.apps.tatasky.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.apps.tatasky.entities.User;
import com.cg.apps.tatasky.payload.BaseResponse;
import com.cg.apps.tatasky.payload.LoginReqPayLoad;
import com.cg.apps.tatasky.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user") //@RequestMapping a specific request path or pattern onto a controller
@Api(value ="User Controller" , description = "Operations on User")
public class UserController {
	
	
	private static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	
	
	
	@Autowired   //To establish the relationship with admin service
	private UserService userService;

	@PostMapping("/save") //PostMapping to insert  the user record 
	@ApiOperation(value ="Register User")
public ResponseEntity<?> saveUser(@Valid @RequestBody User user)
{
		
		logger.info("Enter UserController :: method=saveUser");	
		
 User userObj =  userService.register(user);
 BaseResponse baseResponse = new BaseResponse();
 baseResponse.setStatusCode(1);
 baseResponse.setResponse(userObj);
 baseResponse.setMessage("User Registered Successfully!");
 logger.info("Exit UserController :: method=saveUser"); 
 
 
 return  new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
}	
	
	
@GetMapping("/{id}") //@GetMapping To get the user record based on user id
@ApiOperation(value = "Search user with an ID" , response = User.class)
public ResponseEntity<?> fetchUserDetails(@PathVariable("id")  long id)
{
	logger.info("Enter UserController :: method=fetchUserDetails");	
	
User userObj = userService.findById(id);
BaseResponse baseResponse = new BaseResponse();
baseResponse.setStatusCode(1);
baseResponse.setResponse(userObj);
baseResponse.setMessage("User Details  Found Successfully!");

logger.info("Exit UserController :: method=fetchUserDetails");



return new ResponseEntity<>(baseResponse , HttpStatus.OK);

}


@DeleteMapping("/{id}") //@DeleteMapping To delete user record based on user Id
@ApiOperation(value = "Delete a User")
public ResponseEntity<?> deleteUser(@PathVariable("id") long id)

{
	logger.info("Enter UserController :: method=deleteUser");
	
	User userObj = userService.deleteByUserId(id);

	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(userObj);	
	baseResponse.setMessage("User Deleted Successfully!");
	logger.info("Exit UserController :: method=deleteUser");
	
	return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	

}

@PutMapping("/{id}") // @PutMapping update user details
@ApiOperation(value = "Update User Details")
public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user)
{
	logger.info("Enter UserController :: method=updateUser");
	
	User  userObj = userService.update(id, user);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(userObj);
	baseResponse.setMessage(" User Details Updated Successfully!");
	
	logger.info("Exit UserController :: method=updateUser");
	
	return new ResponseEntity<>(baseResponse , HttpStatus.OK);


}




@PostMapping("/login") //@PostMapping  for login User Details
@ApiOperation(value = "Login ")
public ResponseEntity<?> signIn(@Valid @RequestBody LoginReqPayLoad user )
{
	
	logger.info("Enter UserController :: method=signIn");
	userService.signIn(user);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(user);
	baseResponse.setMessage("User Logged In Successfully!");
	
	logger.info("Exit UserController :: method=signIn");
	
return new ResponseEntity<>(baseResponse, HttpStatus.OK);
}


@PostMapping("/reset") //@PostMapping is for resetting password
@ApiOperation(value ="Reset Password")
public ResponseEntity<?>  changePassword(@Valid @RequestBody LoginReqPayLoad user,String new_password)
{
	logger.info("Enter UserController :: method=changePassword");
	
	
	userService.resetPassword(user,new_password);
	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(1);
	baseResponse.setResponse(user);
	baseResponse.setMessage("Password Changed Successfully");
	
	
	logger.info("Exit UserController :: method=changePassword");
	return new ResponseEntity<>(baseResponse, HttpStatus.OK);
}








}
