package ch.flowtech.TimeTrackBackend.repository;

import ch.flowtech.TimeTrackBackend.model.Role;
import ch.flowtech.TimeTrackBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    @Modifying
    @Query("update User set role = :role where username = :user_name")
    void updateUserRole(@Param("user_name") String username, @Param("role") Role role);

    @Modifying
    @Query("update User set clockedIn = :clockedIn where username = :user_name")
    void updateUserClockedIn(@Param("user_name") String username, @Param("clockedIn") Boolean clockedIn);

}
