package com.atguigu.domain;

import java.util.Date;

/**
 * @author 鄢宇豪
 * @version 1.0
 * @date 2022/1/8 - 13:24
 * 用户信息
 */
public class UserInfo {
    /*
    主键,外键 : id
    姓名 : name
    性别 : sex
    生日 : birthday
    地址 : address
    电话 : phone
     */
    private Integer id;
    private String name;
    private String sex;
    private Date birthday;
    private String address;
    private String phone;

    public UserInfo() {
    }

    public UserInfo(Integer id, String name, String sex, Date birthday, String address, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday +'\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
