package com.colin.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyUser {
	private String username;
	private String password;
	private List<String> roles;
}
