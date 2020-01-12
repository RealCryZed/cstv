package cstv.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

@Data
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private Long id;

//    @Size(min = 1, message = "Username must be at least 4 letters")
//    @Size(max = 50, message = "Username must be lower than 50 letters")
    private String username;

//    @Size(min = 1, message = "Password must be at least 4 letters")
    private String password;

//    @NotNull
//    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
//    @Size(max = 100, message = "Please, enter email, lower than 100 letters")
    private String email;

    private int active;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
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
