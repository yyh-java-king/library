package com.atguigu.domain;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 10:28
 * 用户和管理员的账号密码
 */
public class User {
    /**
     * 主键 : id
     * 读者证号 : userName
     * 密码 : password
     * 用户权限 : status 0 -> 普通用户 1 -> 管理员
     */
    private Integer id;
    private String username;
    private String password;
    private Integer status;

    public User() {
    }

    public User(Integer id, String username, String password, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
