package com.xiasq.bean;

import java.util.Date;

/**
 * @author xiasq
 * @version User, v0.1 2018/3/13 15:22
 */
public class User {
	private Integer id;

	private String username;
	private String password;
	private String mobile;
	private int sex;
	private String email;
	private int cardType;
	private String language;
	private String idcard;
	private String addr;
	private String idcardImgPath;
	private String introduction;
	private Date birthday;
	private Date lastLoginTime;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIdcardImgPath() {
		return idcardImgPath;
	}

	public void setIdcardImgPath(String idcardImgPath) {
		this.idcardImgPath = idcardImgPath;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", mobile='"
				+ mobile + '\'' + ", sex='" + sex + '\'' + ", email='" + email + '\'' + ", cardType=" + cardType
				+ ", language='" + language + '\'' + ", idcard='" + idcard + '\'' + ", addr='" + addr + '\''
				+ ", idcardImgPath='" + idcardImgPath + '\'' + ", introduction='" + introduction + '\'' + ", birthday="
				+ birthday + ", lastLoginTime=" + lastLoginTime + '}';
	}
}