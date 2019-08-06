package com.example.demo.repository;

import java.util.List;

public interface UserRolesRepository {

	 List<String> findRoleByUsername(String username);
}
