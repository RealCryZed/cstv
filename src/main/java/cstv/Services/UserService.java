package cstv.Services;

import cstv.Configs.CachingConfig;
import cstv.Interfaces.RoleRepository;
import cstv.Interfaces.UserRepository;
import cstv.Models.Role;
import cstv.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Service("userService")
@CacheConfig(cacheManager="cacheManager1")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SequenceGeneratorService seqGenerator;

    @Cacheable(value = "user-by-username")
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setId(seqGenerator.generateSequence(User.SEQUENCE_NAME));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(1);
        userRepository.save(user);
    }
}
