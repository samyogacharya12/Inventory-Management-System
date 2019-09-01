package com.example.demo.repository;

import java.util.List;
import java.util.Map;

public interface UserRolesRepository {

	void insertIntoUserRole(Map map);
	void updateIntoUserRole(Map map);
	void deleteIntoUserRole(Map map);
	 List<String> findRoleByUsername(String username);
}
