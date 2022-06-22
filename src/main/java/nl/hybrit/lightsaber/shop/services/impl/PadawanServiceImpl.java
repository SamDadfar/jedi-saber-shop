package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.mapper.PadawanMapper;
import nl.hybrit.lightsaber.shop.mapper.UserMapper;
import nl.hybrit.lightsaber.shop.repository.PadawanRepository;
import nl.hybrit.lightsaber.shop.repository.RoleRepository;
import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.enums.RoleType;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.Role;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import nl.hybrit.lightsaber.shop.services.PadawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private PadawanMapper padawanMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;



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
//    @Transactional
    public PadawanEntity save(PadawanUserModel model) {
        PadawanEntity entity = padawanMapper.map(model);
        entity.setSabers(new ArrayList<>());
        entity.getUser().getRoles().addAll(setUserRoles(model.getRoles()));
        entity.getUser().setEnabled(true);
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
                if (RoleType.fromValue(role) == RoleType.ADMIN_JEDI) {
                    Role adminRole = roleRepository.findByName(RoleType.ADMIN_JEDI)
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
