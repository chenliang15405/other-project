package com.auth2.example.controller;

import com.auth2.example.pojo.AuthCode;
import com.auth2.example.pojo.AuthToken;
import com.auth2.example.service.OauthCodeService;
import com.auth2.example.service.OauthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/oauth2")
public class Oauth2Server {


	@Autowired
	private OauthTokenService oauthTokenService;

	@Autowired
	private OauthCodeService oauthCodeService;

	private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

	private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";


	/**
	 * 向客户端返回授权许可码 code
	 * 参数：
	 * appid  必填
	 * redirect_uri 必填
	 * state 代码中选择，防止攻击参数
	 * <p>
	 * 请求示例：
	 * http://localhost:8080/oauth2/authorize?appid=123&redirect_url=http://localhost:8080/test&state=111
	 * <p>
	 * 响应示例：
	 * http://localhost:8080/test?code=abcd123&state=111
	 * 返回code 并重定向到传递过来的地址
	 *
	 * @return
	 * @throws OAuthProblemException
	 */
	@RequestMapping("/authorize")
	public void applyTempCode(@RequestParam(value = "appid") String appid, @RequestParam(value = "redirect_url") String redirectURL,
									  @RequestParam(value = "state") String state, HttpServletResponse reponse) throws IOException {
		System.out.println("----------服务端/responseCode-------------");
		if (null != appid) {
			AuthCode authCode = oauthCodeService.getCode(appid, redirectURL, state);
			if(authCode != null) {
				reponse.sendRedirect(redirectURL + "?code=" + authCode.getCode() + "&state=" + state);
			}
		}
	}


	/**
	 * 根据获取的code 获取 access_token, 通过grantType='authorization_code' 和appid、appSecert、code
	 * 如果access_token快过期，则通过 grantType='refresh_token'和传入refreshToken 标示更新token
	 *
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/access_token", method = RequestMethod.GET)
	@ResponseBody
	public AuthToken token(@RequestParam(required = false) String code, @RequestParam String grantType, @RequestParam String appid,
							@RequestParam(required = false) String appSecert, @RequestParam(required = false) String refreshToken) {
		System.out.println("--------服务端/responseAccessToken--------");
		AuthToken authToken = new AuthToken();
		// 判断请求类型
		if(grantType.equals(GRANT_TYPE_AUTHORIZATION_CODE)) {
			// 校验code 生成token
			authToken = oauthTokenService.getAccessToken(appid, appSecert, code);
		} else if(grantType.equals(GRANT_TYPE_REFRESH_TOKEN)) {
			// 如果是刷新token，则校验之后，更新token即可
			authToken = oauthTokenService.refreshToken(appid, refreshToken);
		}
		return authToken;
	}


}
