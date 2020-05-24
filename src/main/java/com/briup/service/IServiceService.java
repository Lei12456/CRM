package com.briup.service;

import org.springframework.data.domain.Page;

import com.briup.bean.Customer;
import com.briup.bean.Service;
import com.briup.bean.User;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月16日 下午9:44:57 
* 类说明 
*/
public interface IServiceService {
	//查询出此经理所有的服务
	Page<Service> findAll(String cusName,String type,User creator);
	//进行分页处理
	Page<Service> findAll(String cusName,String type,User creator,Integer pageIndex);
	//添加或者修改
	void saveService(Service service);
	//通过Id查询
	Service findServiceById(Integer id);
	//通过id删除
	void deleteServiceById(Integer id);
	//通过id更新服务 
	void updateServiceByStatus(Integer id);
	//通过id更新服务2
	void updateServiceById(Integer id,String satisfy);
	
	//用户主管部门的服务反馈
	//查询所有的服务第一页
	Page<Service> findAllService(String type,String status);
	
	//进行分页处理
	Page<Service> findAllService(String type,String status,Integer pageIndex);
	
	
}
