package com.blackgaryc.library.domain.user.admin;

import com.blackgaryc.library.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class SimpleUserVO {
    private Long id;
    private String email;
    private String nickname;
    private String account;
    private String avatar;
    private String githubId;

    private Boolean disabled;

    public SimpleUserVO() {
    }

    public SimpleUserVO(UserEntity user) {
        BeanUtils.copyProperties(user,this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
