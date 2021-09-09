package com.colin.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -3977738631880165217L;
	private final long id;
	private final String token;
	private final String username;
	private final List<String> roles;
}
