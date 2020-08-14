package com.auth2.example.dao;


import com.auth2.example.pojo.AuthToken;

public class OauthTokenRepository {


	/**
	 * 需要实际去查询
	 * @param appid
	 * @param refreshToken
	 * @return
	 */
	public AuthToken findAccessToken(String appid, String refreshToken) {
		//TODO 实际查询数据库是否有该条数据
		// sql : select * from table where appid=:appid and refreshToken=:refreshToken
		return null;
	}

	public void update(AuthToken authToken) {
		// TODO 更新为新token
	}

	public void save(AuthToken authToken) {
		// TODO 保存数据库

	}
}
