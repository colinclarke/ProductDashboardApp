package com.colin.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 7166118433971622817L;
	private String username;
	private String password;
}
