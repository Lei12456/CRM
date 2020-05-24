package com.briup.service.impl;
/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月16日 下午9:52:59 
* 类说明 
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.briup.bean.Customer;
import com.briup.bean.Service;
import com.briup.bean.User;
import com.briup.dao.CustomerDao;
import com.briup.dao.ServiceDao;
import com.briup.service.IServiceService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements IServiceService {
		
	@Autowired
	private ServiceDao serDao;
	@Autowired 
	private CustomerDao cusDao;
	
	
	@Override
	public Page<Service> findAll(String cusName, String type,User creator) {
		return findAll(cusName, type,creator, 0);
	}

	@Override
	public Page<Service> findAll(String cusName, String type, User creator,Integer pageIndex) {
		List<Customer> customers=null;
		if (cusName!=null && cusName.trim()!="") {
			 customers = cusDao.findByNameLike(cusName);
		}
		if ((cusName!=null && cusName.trim()!="") && (type!=null && type.trim()!="")) {
			//按照客户名字和类型一起查询
			for (Customer customer : customers) {
				return serDao.findByCustomerLikeAndType(customer.getId(), creator, type, PageRequest.of(pageIndex, 2));
			}
		}else if(cusName!=null && cusName.trim()!="") {
			//按照客户名字模糊查询
			for (Customer customer : customers) {
				return serDao.findByCustomerLike(customer.getId(), creator,PageRequest.of(pageIndex, 2));
			}
		}else if(type!=null && type.trim()!="") {
			//按照服务类型查询
			return serDao.findByTypeAndCreator(type, creator, PageRequest.of(pageIndex, 2));
		}else {
			//无条件查询
			return serDao.findByCreator(creator, PageRequest.of(pageIndex, 2));
		}
		return null;
	}

	@Override
	public void saveService(Service service) {
			serDao.save(service);
	}

	@Override
	public Service findServiceById(Integer id) {
		return serDao.getOne(id);
	}

	@Override
	public void deleteServiceById(Integer id) {
		serDao.deleteById(id);
	}

	@Override
	public void updateServiceByStatus(Integer id) {
		serDao.updateServiceByStatus(id);
	}

	@Override
	public void updateServiceById(Integer id, String satisfy) {
		serDao.updateServiceById(satisfy, id);
	}

	

	@Override
	public Page<Service> findAllService(String type, String status) {
		return findAllService(type, status,0);
	}
	//对服务反馈模块进行逻辑处理
	@Override
	public Page<Service> findAllService(String type, String status, Integer pageIndex) {
		if ((type!=null && type.trim()!="") && (status!=null && status.trim()!="")) {
			//通过类型和状态一起查询
			return serDao.findByTypeAndStatus(type, status, PageRequest.of(pageIndex, 2));
		}else if((type!=null && type.trim()!="")) {
			//通过类型查询
			return serDao.findByType(type, PageRequest.of(pageIndex, 2));
		}else if(status!=null && status.trim()!="") {
			//通过状态查询
			return serDao.findByStatus(status, PageRequest.of(pageIndex, 2));
		}else {
			//无条件查询所有
			return serDao.findAll(PageRequest.of(pageIndex, 2));
		}
		
	}


	
}
