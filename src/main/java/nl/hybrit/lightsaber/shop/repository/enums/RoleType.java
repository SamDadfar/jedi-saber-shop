package nl.hybrit.lightsaber.shop.repository.enums;

import java.util.Arrays;

public enum RoleType {
    ADMIN_JEDI,
    USER_PADAWAN;

    public static RoleType fromValue(String value) {
        return Arrays.stream(RoleType.values())
                .filter(role -> value.equalsIgnoreCase(role.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("given role is not exist."));
    }
    }
