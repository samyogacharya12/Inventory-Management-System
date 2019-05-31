package com.example.demo.repository;

import java.util.List;

public interface UserRolesRepository {

	public List<String> findRoleByUsername(String username);
}
