package com.auth2.example.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthToken {

	private String accessToken;

	private String refreshToken;

	private Date expireIn;

	private String status;

	private String appid;

}
