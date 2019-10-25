package pl.codeconcept.e2d.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codeconcept.e2d.database.entity.UserActivity;

@Repository
public interface UserActivityRepo extends JpaRepository<UserActivity,Long> {
}
