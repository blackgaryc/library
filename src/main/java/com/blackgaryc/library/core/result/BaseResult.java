package com.blackgaryc.library.core.result;

public class BaseResult {
    private int code;
    private long timestamp;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", timestamp=" + timestamp +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public static final class Builder {
        private int code;
        private long timestamp;
        private Object data;
        private String message;

        private Builder() {
        }

        public static Builder result() {
            return new Builder();
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public BaseResult build() {
            BaseResult baseResult = new BaseResult();
            baseResult.setCode(code);
            baseResult.setTimestamp(timestamp);
            baseResult.setData(data);
            baseResult.setMessage(message);
            return baseResult;
        }
    }
}
