package com.fz.jdbc.datasource;

import java.util.Date;

/**
 * Author:fz
 * Date:2022/11/14 18:24
 * Actor对象和actor记录对应
 */
public class Actor {
  private Integer id;
  private String name;
  private String sex;
  private Date borndate;
  private String phone;

  public Actor(){
  }

  public Actor(Integer id, String name, String sex, Date borndate, String phone) {
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.borndate = borndate;
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

  public Date getBorndate() {
    return borndate;
  }

  public void setBorndate(Date borndate) {
    this.borndate = borndate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "Actor{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", borndate=" + borndate +
            ", phone='" + phone + '\'' +
            '}';
  }
}
