package org.tievoli.model;

import java.util.Date;

public class User {
	private Integer userId;

	private String userName;

	private String phone;

	private String email;

	private String password;

	private Integer loginTimes;

	private Integer sex;

	private Integer age;

	private Date regDtm;

	private Integer validPhone;

	private Integer validEmail;

	private String nickName;

	private String signuature;

	private Integer articleNum;

	private Integer commentNum;

	private Date activeDtm;

	private Integer userLevel;

	private Integer userStatus;

	private String avatar;

	private String qq;

	private String weibo;

	private String weixin;

	private Date lastLoginDtm;

	private Date loginDtm;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	public Integer getValidPhone() {
		return validPhone;
	}

	public void setValidPhone(Integer validPhone) {
		this.validPhone = validPhone;
	}

	public Integer getValidEmail() {
		return validEmail;
	}

	public void setValidEmail(Integer validEmail) {
		this.validEmail = validEmail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getSignuature() {
		return signuature;
	}

	public void setSignuature(String signuature) {
		this.signuature = signuature == null ? null : signuature.trim();
	}

	public Integer getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(Integer articleNum) {
		this.articleNum = articleNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Date getActiveDtm() {
		return activeDtm;
	}

	public void setActiveDtm(Date activeDtm) {
		this.activeDtm = activeDtm;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo == null ? null : weibo.trim();
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin == null ? null : weixin.trim();
	}

	public Date getLastLoginDtm() {
		return lastLoginDtm;
	}

	public void setLastLoginDtm(Date lastLoginDtm) {
		this.lastLoginDtm = lastLoginDtm;
	}

	public Date getLoginDtm() {
		return loginDtm;
	}

	public void setLoginDtm(Date loginDtm) {
		this.loginDtm = loginDtm;
	}
}