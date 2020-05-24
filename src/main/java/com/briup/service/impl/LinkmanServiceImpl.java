package com.briup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.briup.bean.Customer;
import com.briup.bean.Linkman;
import com.briup.dao.LinkmanDao;
import com.briup.service.ILinkmanService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月5日 下午10:52:54 
* 类说明 
*/
@Service
public class LinkmanServiceImpl implements ILinkmanService {
	
	@Autowired
	private LinkmanDao linkDao;

	@Override
	public Page<Linkman> findAllLinkman(Integer cusId) {
		return findAllLinkman(cusId,0);
	}

	@Override
	public Page<Linkman> findAllLinkman(Integer cusId,Integer pageIndex) {
		Customer customer = new Customer();
		customer.setId(cusId);
		return linkDao.findByCustomer(customer,PageRequest.of(pageIndex, 2));
	}
	@Override
	public void saveLinkman(Linkman linkman) {
		linkDao.save(linkman);
	}
	@Override
	public Linkman findById(Integer id) {
		return linkDao.getOne(id);
	}


	@Override
	public void deleteById(Integer id) {
		linkDao.deleteById(id);
	}



}
