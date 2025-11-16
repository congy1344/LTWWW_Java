package iuh.fit.se.seeds;


import iuh.fit.se.entities.Permission;
import iuh.fit.se.entities.Role;
import iuh.fit.se.entities.User;
import iuh.fit.se.repositories.PermissionRepository;
import iuh.fit.se.repositories.RoleRepository;
import iuh.fit.se.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(PermissionRepository permissionRepository,
                      RoleRepository roleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            Permission permissionRead = permissionRepository.save(new Permission(null, "DOCUMENT:READ"));
            Permission permissionWrite = permissionRepository.save(new Permission(null, "DOCUMENT:WRITE"));

            Role roleUser = roleRepository.save(new Role(null, "ROLE_USER", Set.of()));
            Role roleAmin = new Role(null, "ROLE_ADMIN", Set.of(permissionRead, permissionWrite));
            roleRepository.saveAll(List.of(roleUser, roleAmin));

            // User 1: chỉ READ
            User userRead = new User();
            userRead.setUsername("user_read");
            userRead.setPassword(passwordEncoder.encode("123456"));
            userRead.setRoles(Set.of(roleUser));
            userRead.setPermissions(Set.of(permissionRead));

            // User 2: chỉ WRITE
            User userWrite = new User();
            userWrite.setUsername("user_write");
            userWrite.setPassword(passwordEncoder.encode("123456"));
            userWrite.setRoles(Set.of(roleUser));
            userWrite.setPermissions(Set.of(permissionWrite));

            // User 3: có cả READ + WRITE
            User userFull = new User();
            userFull.setUsername("user_full");
            userFull.setPassword(passwordEncoder.encode("123456"));
            userFull.setRoles(Set.of(roleUser));
            userFull.setPermissions(Set.of(permissionRead, permissionWrite));

            // User 4: Admin có cả READ + WRITE
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRoles(Set.of(roleAmin));
            admin.setPermissions(Set.of(permissionRead, permissionWrite));

            userRepository.saveAll(List.of(userRead, userWrite, userFull, admin));
        }
    }
}
