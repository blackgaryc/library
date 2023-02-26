package com.blackgaryc.library.core.register;

import com.blackgaryc.library.core.error.VerificationCodeErrorException;
import com.blackgaryc.library.core.error.VerificationCodeExpressException;
import com.blackgaryc.library.core.error.VerificationCodeNotExistException;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class to store verification code in local
 */
public class DefaultVerificationCodeService implements VerificationCodeService {
    public static int VERIFICATION_CODE_TIME2LIVE = 60 * 10; //600s
    private static final Map<String, Data> HASH_MAP = new ConcurrentHashMap<>();

    @Override
    public void save(@NotNull String user, @NotNull String code) {
        HASH_MAP.put(user, new Data(code));
    }

    @Override
    public boolean check(String user, String code) throws VerificationCodeExpressException, VerificationCodeErrorException, VerificationCodeNotExistException {
        Data data = HASH_MAP.get(user);
        if (null == data) {
            throw new VerificationCodeNotExistException();
        }
        if (!data.getCode().equals(code)) {
            throw new VerificationCodeErrorException();
        }
        if (!Instant.now().isBefore(data.getTimeCreate().plusSeconds(data.getTimeToLive()))) {
            throw new VerificationCodeExpressException();
        }
        HASH_MAP.remove(user);
        return true;
    }

    private static class Data {
        private final String code;
        private final Instant timeCreate;

        private int timeToLive = VERIFICATION_CODE_TIME2LIVE;

        public Data(String code, int timeToLive) {
            this.timeCreate = Instant.now();
            this.code = code;
            this.timeToLive = timeToLive;
        }

        public Data(String code) {
            this.timeCreate = Instant.now();
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public Instant getTimeCreate() {
            return timeCreate;
        }

        public int getTimeToLive() {
            return timeToLive;
        }
    }
}
