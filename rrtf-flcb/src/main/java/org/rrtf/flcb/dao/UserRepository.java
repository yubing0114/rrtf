package org.rrtf.flcb.dao;


import org.rrtf.flcb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(int userId);
	User findByUsername(String username);
}
