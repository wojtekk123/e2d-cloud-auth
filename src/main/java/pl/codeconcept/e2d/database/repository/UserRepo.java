package pl.codeconcept.e2d.database.repository;

import pl.codeconcept.e2d.database.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
UserRepo extends JpaRepository<UserRegistration, Long> {

    UserRegistration findByUsername(String username);


}
