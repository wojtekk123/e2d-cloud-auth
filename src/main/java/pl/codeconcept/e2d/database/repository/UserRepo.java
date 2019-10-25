package pl.codeconcept.e2d.database.repository;

import pl.codeconcept.e2d.database.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
UserRepo extends JpaRepository<Users,Long> {

 Users findByUsername(String username);
 boolean existsByUsername(String username);
}
