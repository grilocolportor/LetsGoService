package org.avs.control;

import java.util.List;

import org.avs.bean.User;
import org.avs.repository.UserRepository;

public class UserControl {
	public static User addUser(User user){
		return UserRepository.addUser(user);
	}
	
	public static List<User> getUserByParam(User user){
		return UserRepository.getUserByParam(user);
	}
	
}
