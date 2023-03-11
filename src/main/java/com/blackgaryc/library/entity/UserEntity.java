package com.blackgaryc.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
public class UserEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    /**
     * 
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 
     */
    @TableField(value = "account")
    private String account;

    /**
     * 
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 
     */
    @TableField(value = "github_id")
    private String githubId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 
     */
    public String getAccount() {
        return account;
    }

    /**
     * 
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     */
    public String getGithubId() {
        return githubId;
    }

    /**
     * 
     */
    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserEntity other = (UserEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getGithubId() == null ? other.getGithubId() == null : this.getGithubId().equals(other.getGithubId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getGithubId() == null) ? 0 : getGithubId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", account=").append(account);
        sb.append(", avatar=").append(avatar);
        sb.append(", githubId=").append(githubId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}