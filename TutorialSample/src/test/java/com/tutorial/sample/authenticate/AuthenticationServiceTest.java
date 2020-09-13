/**
 * 
 */
package com.tutorial.sample.authenticate;

/**
 * @author Marcelo
 *
 */

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.tutorial.sample.authenticate.AuthenticationService;
import com.tutorial.sample.authenticate.UserInfo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

	UserInfo userInfo1;
	UserInfo mockedUserInfol;
	Dao dao;
	
	@Before
	public void SetUp()
	{
		mockedUserInfol = new UserInfo("user1", "id", "password1");
	}
	
	public void CreateMocks(String name)
	{
		dao = mock(Dao.class);
		when(dao.getUser(name)).thenReturn(mockedUserInfol);
	}
	
	public void CreateMocksNull(String name)
	{
		dao = mock(Dao.class);
		when(dao.getUser(name)).thenReturn(null);
	}
	
	@Test
	public void AuthorizedUserTest()
	{
		userInfo1 = new UserInfo("user1", "id", "password1");
		String name = "user1";
		CreateMocks(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(dao);
		
		Boolean actual = authenticationService.Authorize(userInfo1);
		
		Mockito.verify(dao).getUser(name);
		
		assertEquals(true, actual);
	}
	
	@Test
	public void NotAuthorizedUserIdTest()
	{
		userInfo1 = new UserInfo("user1", "id2", "password1");
		String name = "user1";
		CreateMocks(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(dao);
		
		Boolean actual = authenticationService.Authorize(userInfo1);
		
		Mockito.verify(dao).getUser(name);
		
		assertEquals(false, actual);
	}
	
	@Test
	public void NotAuthorizedUserPasswordTest()
	{
		userInfo1 = new UserInfo("user1", "id", "password2");
		String name = "user1";
		CreateMocks(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(dao);
		
		Boolean actual = authenticationService.Authorize(userInfo1);
		
		Mockito.verify(dao).getUser(name);
		
		assertEquals(false, actual);
	}
	
	@Test
	public void NotAuthorizedUsernameTest()
	{
		userInfo1 = new UserInfo("user2", "", "password1");
		String name = "user2";
		CreateMocksNull(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(dao);
		
		Boolean actual = authenticationService.Authorize(userInfo1);
		
		Mockito.verify(dao).getUser(name);
		
		assertEquals(false, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NullDaoTest()
	{
		userInfo1 = new UserInfo("user2", "", "password1");
		String name = "user1";
		CreateMocks(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NullUserInfoTest()
	{
		userInfo1 = null;
		String name = "user1";
		CreateMocks(name);
		AuthenticationService authenticationService = 
					new AuthenticationService(dao);
		
		Boolean actual = authenticationService.Authorize(userInfo1);
	}
	
	@After
	public void CleanUp()
	{
		userInfo1 = null;
		mockedUserInfol = null;
		dao = null;
	}
}
