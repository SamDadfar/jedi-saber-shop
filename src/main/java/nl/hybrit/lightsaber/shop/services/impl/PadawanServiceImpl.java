package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.mapper.PadawanMapper;
import nl.hybrit.lightsaber.shop.repository.PadawanRepository;
import nl.hybrit.lightsaber.shop.repository.RoleRepository;
import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.enums.RoleType;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.Role;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class PadawanServiceImpl implements PadawanService {

    @Autowired
    private PadawanRepository padawanRepository;
    @Autowired
    private PadawanMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public final static float ENOUGH_FORCE_AS_JEDI = 93.2f;
    public final static int ADULT_AGE = 18;
    public final static int JEDI_DISSOLVE_CONSTANT = 140;

    @Override
    public List<PadawanEntity> findAll() {
        return padawanRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PadawanEntity findById(long id) {
        return padawanRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public PadawanEntity save(PadawanUserModel model) {
        PadawanEntity entity = mapper.map(model);
        entity.getUser().getRoles().addAll(setUserRoles(model.getRole()));
        return padawanRepository.save(entity);
    }

    private Set<Role> setUserRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleType.USER_PADAWAN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equalsIgnoreCase("admin")) {
                    Role adminRole = roleRepository.findByName(RoleType.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(RoleType.USER_PADAWAN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        return roles;
    }
}
