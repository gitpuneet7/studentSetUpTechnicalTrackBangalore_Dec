/**
 * 
 */
package com.tutorial.sample.authenticate;

/**
 * @author Marcelo
 *
 */
public class AuthenticationService {

	private Dao dao;
	public AuthenticationService(Dao dao) {
		if (dao == null) throw new IllegalArgumentException("dao is null");
		this.dao = dao;
		// TODO Auto-generated constructor stub
	}

	public Boolean Authorize(UserInfo userInfo) {
		//Scenario 1: Input is null
		if (userInfo == null) throw new IllegalArgumentException("userInfo is null");
		// TODO Auto-generated method stub
		
		UserInfo user = dao.getUser(userInfo.name);
		
		//Scenario 2: user not found in the database
		if (user == null) return false;
		
		System.out.println(user.name + " " + user.id + " " + user.password);
		System.out.println(userInfo.name + " " + userInfo.id + " " + userInfo.password);

		//Scenario 3: ids are different 
		if (user.id != userInfo.id) return false;
				
		//Scenario 4: passwords are different
		if (user.password != userInfo.password) return false;
		
		//Got here: name, id, and password are correct
		return true;
	}
}
