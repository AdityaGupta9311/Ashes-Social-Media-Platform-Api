package com.SocialmediaAPI.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SocialmediaAPI.models.User;
import java.util.List;


@Repository
public interface UserRepositry extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	@Query("select u from User u where u.firstname LIKE%:query% OR u.lastname LIKE%:query% OR u.email LIKE%:query%")
	public List<User> searchUser(@Param("query") String query);

}
