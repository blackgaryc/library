package com.blackgaryc.library.core.register;

import com.blackgaryc.library.myservice.impl.AbstractUserRegisterService;

import java.util.EnumMap;
import java.util.Map;

/**
 * factory to load service by enum
 */
public class UserRegisterFactory {
    private static final Map<RegisterTypeEnum, AbstractUserRegisterService> instances = new EnumMap<>(RegisterTypeEnum.class);

    public <T extends AbstractUserRegisterService> void apply(RegisterTypeEnum type, T service) {
        instances.put(type, service);
    }

    public AbstractUserRegisterService getService(RegisterTypeEnum registerTypeEnum) {
        if (null == registerTypeEnum) {
            throw new RuntimeException("unable to find registerService [empty]");
        }
        AbstractUserRegisterService userRegisterService = instances.get(registerTypeEnum);
        if (null == userRegisterService) {
            throw new RuntimeException("unable to find registerService [" + registerTypeEnum + "]");
        }
        return userRegisterService;
    }
}
