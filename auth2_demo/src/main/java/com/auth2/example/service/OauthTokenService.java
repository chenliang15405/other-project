package com.auth2.example.service;

import com.auth2.example.dao.OauthTokenRepository;
import com.auth2.example.pojo.AuthCode;
import com.auth2.example.pojo.AuthToken;
import com.auth2.example.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OauthTokenService {

	@Autowired
	private OauthCodeService oauthCodeService;

	@Autowired
	private OauthTokenRepository oauthTokenRepository;


	// 2小时的毫秒数
	private static final Long EXPIRED_MILLI_SECOND_HOUR = 7200000L;

	private static final int EXPIRED_DAYS = 1;

	/**
	 * 校验信息，并生成token
	 * @param appid
	 * @param appSecert
	 * @param code
	 * @return
	 */
	public AuthToken getAccessToken(String appid, String appSecert, String code) {
		boolean permission = checkPermission(appid, appSecert, code);
		if(permission) {
			// 生成code，设置过期时间，保存数据库 ----伪代码
			long expiredTime = System.currentTimeMillis() + EXPIRED_MILLI_SECOND_HOUR * EXPIRED_DAYS;
			String token = EncryptUtil.MD5(expiredTime + appid);
			String refreshToken = EncryptUtil.MD5(System.currentTimeMillis() + appid);

			AuthToken authToken = new AuthToken();
			authToken.setAccessToken(token);
			authToken.setRefreshToken(refreshToken);
			authToken.setExpireIn(new Date(expiredTime));
			authToken.setAppid(appid);
			// 保存生成的token
			oauthTokenRepository.save(authToken);

			return authToken;
		}
		return null;
	}


	/**
	 * 校验权限
	 */
	private boolean checkPermission(String appid, String appSecert, String code) {
		// TODO 实际需要查询数据库的appid、appsecert表---- 伪代码
		//findByAppidAndSecert(appid, appSecert);

		// TODO  如果appid、 appsecert验证同难过，然后查询code
		AuthCode authCode = oauthCodeService.checkCodeValid(code);

		// 如果code没有过期，则校验通过
		if(authCode.getExpireIn().before(new Date())) {
			// 然后设置code过期，保证该code只能请求一次（防止无数次请求获取新token，只让使用refresh_token去刷新token即可）
			authCode.setExpireIn(new Date(System.currentTimeMillis() - EXPIRED_MILLI_SECOND_HOUR));
			oauthCodeService.update(authCode);

			return true;
		}
		return false;
	}


	/**
	 * 当token 过期时，刷新token重置过期时间
	 * @param appid
	 * @param refreshToken
	 * @return
	 */
	public AuthToken refreshToken(String appid, String refreshToken) {
		// 伪代码，根据 appid、refreshToken 查询authToken表中是否存在对象
		 AuthToken authToken = oauthTokenRepository.findAccessToken(appid, refreshToken);
		if(authToken != null) {
			// 更新token
			long expiredTime = System.currentTimeMillis() + EXPIRED_MILLI_SECOND_HOUR * EXPIRED_DAYS;

			String newToken = EncryptUtil.MD5(expiredTime + appid);
			String newRefreshToken = EncryptUtil.MD5(System.currentTimeMillis() + appid);
			authToken.setAccessToken(newToken);
			authToken.setRefreshToken(newRefreshToken);
			authToken.setExpireIn(new Date(expiredTime));
			// 使用新token 更新过期的token
			oauthTokenRepository.update(authToken);
			return authToken;
		}
		return null;
	}

}
