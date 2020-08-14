package com.auth2.example.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AuthCode {

	private String code;

	private Date expireIn;

}
