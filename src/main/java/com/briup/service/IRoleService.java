package com.briup.service;
/** 
* @author 作者 angel: 
* @version 创建时间：2020年3月27日 下午2:34:30 
* 类说明 
*/

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.briup.bean.Role;

public interface IRoleService {
	//查询所有刚进入角色管理的信息
	Page<Role>  findAllRoles();
	//查询指定页面上的信息
	Page<Role> findAllRoles(Integer pageIndex);
	//新增
	void saveOrUpdateRole(Role role);
	//通过Id查询
	Role findRoleById(Integer id);
	//删除
	void deleteRole(Integer id);
	//查询所有角色信息
	public List<Role> findRoles();
	
}
