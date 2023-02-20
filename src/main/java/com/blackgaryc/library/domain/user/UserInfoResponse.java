package com.blackgaryc.library.domain.user;

import com.blackgaryc.library.entity.UserEntity;

public class UserInfoResponse {
    private Long id;
    private String nickname;
    private String avatar;
    private String email;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserInfoResponse() {
    }
    public UserInfoResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.avatar = userEntity.getAvatar();
        this.nickname = userEntity.getNickname();
        this.phone = "";
        this.email = userEntity.getEmail();
    }

}
