package com.briup.service;



import org.springframework.data.domain.Page;

import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年3月26日 下午4:47:51 
* 类说明 
*/
public interface IUserService {
	public void saveUser(User user);
	public User findByName(String name);
	public User findUserById(Integer id);
	public void deleteUserById(Integer id);
	public Page<User> findUsersByRole(Integer roleId,Integer pageIndex);
	public Page<User> findUsersByRole(Integer roleId);
}
