package pl.codeconcept.e2d.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codeconcept.e2d.database.entity.UserLogin;

@Repository
public interface LoginRepo extends JpaRepository<UserLogin,Long> {
}
