package org.neau.rrtf.ArtiJPA;



import org.neau.rrtf.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
   User findByUsername(String username);

User findByUserId(int userId);

}
