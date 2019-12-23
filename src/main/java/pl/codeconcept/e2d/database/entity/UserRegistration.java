package pl.codeconcept.e2d.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.codeconcept.e2d.database.entity.enums.RoleType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_registration")
public class UserRegistration implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull

    @Column(name = "user_name")
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name = "create_date")
    private UserActivity userActivity;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

