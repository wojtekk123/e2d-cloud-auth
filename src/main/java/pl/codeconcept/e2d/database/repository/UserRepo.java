package pl.codeconcept.e2d.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codeconcept.e2d.database.entity.UserRegistration;

@Repository
public interface
UserRepo extends JpaRepository<UserRegistration, Long> {

    UserRegistration findByUsername(String username);
}
