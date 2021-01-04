package com.cg.apps;
import static  com.cg.apps.tatasky.util.AppConstants.USER_NOT_FOUND_CONST;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.apps.tatasky.entities.Account;
import com.cg.apps.tatasky.entities.User;
import com.cg.apps.tatasky.exceptions.ResourceNotFoundException;
import com.cg.apps.tatasky.exceptions.UserAlreadyExistException;
import com.cg.apps.tatasky.payload.LoginReqPayLoad;
import com.cg.apps.tatasky.repository.UserRepository;
import com.cg.apps.tatasky.service.UserServiceImpl;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@DisplayName("Sample test")
	
	@Test
	void sampleTest()
	{
		
		assertTrue(true);
		
	}
	
	
	@Test
	void testRegisterUser()
	{
		User user = new User();
		Account account = new Account();
		account.setAccountId(212);
		user.setId(112);
		user.setAccount(account);
		user.setFirstName("Robert");
		user.setLastName("Pattinson");
		user.setPassword("Robert@123");
		user.setRoll("user");
		user.setUsername("___Robert__");
		
		given(userRepository.save(user)).willReturn(user);
		User savedUser = userService.register(user);
		Assertions.assertThat(savedUser).isNotNull();
		verify(userRepository).save(any(User.class));
		
		
	}


@Test
void testFindUserById()
{
final long userId = 900;
Account acc = new Account();
acc.setAccountId(303);
User user = new User();
user.setId(userId);
user.setFirstName("Dummy");
user.setLastName("User");
user.setUsername("__DummyUser__");
user.setPassword("Dummy@123");
user.setRoll("User");
user.setAccount(acc);

given(userRepository.findById(userId)).willReturn(Optional.of(user));
final User expected = userService.findById(userId);
Assertions.assertThat(expected).isNotNull();


}
	

	
@Test
public void shouldBeDeleted()
{
User user = new User();
Account acc = new Account();
acc.setAccountId(565);
final long userId = 1099;
user.setAccount(acc);
user.setFirstName("Dummy");
user.setId(userId);
user.setLastName("User");
user.setPassword("DummyUsr@123");
user.setRoll("user");
user.setUsername("__Dummy__");
given(userRepository.findById(userId)).willReturn(Optional.of(user));
userService.deleteByUserId(userId);

userService.deleteByUserId(userId);
verify(userRepository,times(2)).delete(user);

}




@Test
public void  shouldUpdateUser()
{
	Account acc = new Account();
	acc.setAccountId(455);
	long userId =444;
User user = new User();
user.setFirstName("Dummy");
user.setAccount(acc);
user.setLastName("Users");
user.setId(userId);
user.setPassword("Dummy@123");
user.setRoll("users");
user.setUsername("__Dummy__");

given(userRepository.save(user)).willReturn(user);
given(userRepository.findById(userId)).willReturn(Optional.of(user));
User expectedUser = userService.update(user.getId(), user);
Assertions.assertThat(expectedUser).isNotNull();


}


@Test
public void testFindUserByIdWhenExceptionThrown() {
	
    Account acc = new Account();
    User user  = new User();
    acc.setAccountId(666);
    user.setAccount(acc);
    user.setFirstName("Dummy");
    user.setId(777);
    user.setLastName("User");
    user.setPassword("User@123");
    user.setRoll("User");
    user.setUsername("__username__");
	final long userId = 1000;
	
	Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
    	userService.findById(userId);
    });		
    String expectedMessage = "User not found for this id: "+userId;
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage,expectedMessage);
}




@Test
public void shouldThrowErrorWhenSaveExistingUserWithExistingId() {
	
    Account acc = new Account();
    User user  = new User();
    acc.setAccountId(666);
    user.setAccount(acc);
    user.setFirstName("Dummy");
    user.setId(777);
    user.setLastName("User");
    user.setPassword("User@123");
    user.setRoll("User");
    user.setUsername("__username__");
	
	given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
	assertThrows(UserAlreadyExistException.class,()-> {
		userService.register(user);
		
	});
	
	verify(userRepository,never()).save(any(User.class));
	
}

@Test
public void NotFoundDeleted()

{
	long userId =1000;
	Exception exception = assertThrows(ResourceNotFoundException.class,()->{userService.deleteByUserId(userId);});
	String expectedMessage = USER_NOT_FOUND_CONST +userId;
	String actualMessage = exception.getMessage();
	assertEquals(actualMessage,expectedMessage);
	
	
	}


@Test
void testLogin()
{
	
String str1 = "User Logged In Successfully!";
Account acc = new Account();
acc.setAccountId(99);
User user = new User();
user.setAccount(acc);
user.setFirstName("Dummy");
user.setId(33);
user.setLastName("Users");
user.setPassword("Dummy@123");
user.setRoll("User");
user.setUsername("__Dummy__");

LoginReqPayLoad loginUser = new LoginReqPayLoad();
loginUser.setUserId(33);
loginUser.setPassword("Dummy@123");
 given(userRepository.findById(loginUser.getUserId())).willReturn(Optional.of(user));
 
String str2 = userService.signIn(loginUser);
verify(userRepository,times(1)).findById(loginUser.getUserId());
assertEquals(str1,str2);
}



@Test
void testResetPassword()
{
	
String str1 = "Password Changed Successfully!";
Account acc = new Account();
acc.setAccountId(99);
User user = new User();
user.setAccount(acc);
user.setFirstName("Dummy");
user.setId(33);
user.setLastName("Users");
user.setPassword("Dummy@123");
user.setRoll("User");
user.setUsername("__Dummy__");

LoginReqPayLoad loginUser = new LoginReqPayLoad();
loginUser.setUserId(33);
loginUser.setPassword("Dummy@123");
given(userRepository.findById(loginUser.getUserId())).willReturn(Optional.of(user));

String str2 = userService.resetPassword(loginUser,"Shrinda@123");
verify(userRepository,times(1)).findById(loginUser.getUserId());
assertEquals(str1,str2);


}


@Test
void shouldThrowErrorForInvalidUser()
{
	
	LoginReqPayLoad  loginUser = new LoginReqPayLoad();

	loginUser.setUserId(777);
	loginUser.setPassword("password@123");
	

	Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
    	userService.signIn(loginUser);
    });	
	
    String expectedMessage = USER_NOT_FOUND_CONST +loginUser.getUserId();
    String actualMessage = exception.getMessage();
    assertEquals(actualMessage,expectedMessage);
}




	






	
}
