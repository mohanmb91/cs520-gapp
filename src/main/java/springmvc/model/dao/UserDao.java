package springmvc.model.dao;

import java.util.List;

import springmvc.model.*;

public interface UserDao {

    User getUser( Integer id );

    List<User> getUsers();
    
	User saveUser(User user);

	UserRole getUserRoleById(Integer id);

	UserRole getUserRoleByName(String role);
    
    
}