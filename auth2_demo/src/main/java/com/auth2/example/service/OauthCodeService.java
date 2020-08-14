package com.auth2.example.service;

import com.auth2.example.pojo.AuthCode;
import com.auth2.example.util.EncryptUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OauthCodeService {

	/**
	 * 2小时的毫秒数
	 */
	private static final Long EXPIRED_MILLI_SECOND_HOUR = 7200000L;

	/**
	 * 校验appid 和 redirectUrl是否和数据库中一致，生成 code返回，并保存数据库
	 * @param appid
	 * @param redirectURL
	 * @param state
	 * @return
	 */
	public AuthCode getCode(String appid, String redirectURL, String state) {
		boolean flag = checkApp(appid, redirectURL);
		if(flag) {
			String code = EncryptUtil.MD5(state + System.currentTimeMillis());

			AuthCode authCode = new AuthCode();
			authCode.setCode(code);
			authCode.setExpireIn(new Date(System.currentTimeMillis() + EXPIRED_MILLI_SECOND_HOUR));

			// TODO 实际需要保存到数据库
			// codeRepository.save(wkfAuthPermissionCode);
			return authCode;
		}

		return null;
	}

	/**
	 * 伪代码，实际需要查询数据库
	 */
	private boolean checkApp(String appid, String redirectURL) {
		// TODO 实际查询数据库进行校验
		if(appid.equals(123) && redirectURL.equals("http://localhost:8080/test")) {
			return true;
		}
		return false;
	}

	/**
	 * 查询code是否过期
	 * @param code
	 * @return
	 */
	public AuthCode checkCodeValid(String code) {
		// TODO 实际查询数据库
		// 伪代码
//		AuthCode authCode = findByCode(code);
		return null;
	}


	public void update(AuthCode authCode) {
		// TODO 更新code
	}
}
