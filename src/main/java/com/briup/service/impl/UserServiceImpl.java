package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briup.bean.Role;
import com.briup.bean.User;
import com.briup.dao.RoleDao;
import com.briup.dao.UserDao;
import com.briup.service.IUserService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年3月26日 下午4:51:09 
* 类说明 和User用户相关的逻辑
*/
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public User findUserById(Integer id) {
		return userDao.getOne(id);
	}

	@Override
	public void deleteUserById(Integer id) {
		userDao.deleteUserById(id);
	}
	//没有条件查询时的User
	@Override
	public Page<User> findUsersByRole(Integer roleId) {
		return findUsersByRole(roleId, 0);
	}
	//有条件查询时的User 分页
	@Override
	public Page<User> findUsersByRole(Integer roleId,Integer pageIndex) {
		Page<User> users = null;
		if(roleId == null) {
			users = userDao.findAll(PageRequest.of(pageIndex, 3));
			return users;
		}else {
			Role role = new Role();
			role.setId(roleId);
			users = userDao.findByRole(role, PageRequest.of(pageIndex, 3));
			return users;
		}
		
	}


}
