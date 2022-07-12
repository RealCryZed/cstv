package cstv.Services;

import cstv.Interfaces.RoleRepository;
import cstv.Interfaces.UserRepository;
import cstv.Models.Role;
import cstv.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
@CacheConfig(cacheManager="cacheManager1")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    /**
     * Finds user with specific name in database.
     * @param username name related to user
     * @return user that has been found in database
     */
    @Cacheable(value = "user-by-username")
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Method for the registration.html page.
     * Saves user with given credentials in database.
     * @param user element of User class, which takes all credentials
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(seqGenerator.generateSequence(User.SEQUENCE_NAME));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(1);
        userRepository.save(user);
    }
}
