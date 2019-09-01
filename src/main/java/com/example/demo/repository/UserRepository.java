package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.model.LogRecord;
import com.example.demo.model.Projectuser;
import com.example.demo.model.Usertemp;

public interface UserRepository {

  Projectuser findByUsername(String username);
  Projectuser getUserIdByUsername(String username);
   void Insert(Projectuser projectuser);
  Projectuser getUserById(long id);
   void deleteUserInfo(long user_id);
   List<Projectuser> getAllUserInfo();
   void updateIntoUser(Projectuser user);
  void insetIntoUserTemp(Map map);
  void insertIntoLogRecord(Map map);
  void updateIntoLogRecord(Map map);
  List<LogRecord> getListLogRecord(Date logoutTime);
//  Map getUserTempData();
  void deleteIntoUserTemp(String username);
   int getTotalNoOfQuantity(String product_name);
   int getNoofUsers();
}
