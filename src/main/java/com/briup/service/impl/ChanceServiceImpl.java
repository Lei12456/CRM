package com.briup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.briup.bean.Chance;
import com.briup.dao.ChanceDao;
import com.briup.service.IChanceService;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午2:07:23 
* 类说明 	销售商机service实现类
*/
@Service
public class ChanceServiceImpl implements IChanceService {
	@Autowired
	private ChanceDao chDao;
	
	@Override
	public Page<Chance> findAllChances(String name,String address) {
		return findAllChances(name,address,0);
	}

	@Override
	public Page<Chance> findAllChances(String name,String address,Integer pageIndex) {
		if((name!=null && name.trim()!="") && (address!=null && address.trim()!="")) {
			//按照客户名称和地区一起查询
			System.out.println(name+"--1--"+address);
			return chDao.findByCustomerAndAddress(name, address, PageRequest.of(pageIndex, 2));
		}else if(address!=null && address.trim()!=""){
			//按照地区进行查询
			System.out.println(name+"--2--"+address);
			return  chDao.findByAddress(address,  PageRequest.of(pageIndex, 2));
		}else if (name!=null && name.trim()!="") {
			//按照客户名称模糊查询
			System.out.println(name+"--3--"+address);
			return  chDao.findByCustomerLike(name, PageRequest.of(pageIndex, 2));
		}else {
			//无条件查询所有的
			System.out.println(name+"--4--"+address);
			return chDao.findAll(PageRequest.of(pageIndex, 2));
		}
	}

	@Override
	public void saveChance(Chance chance) {
		chDao.save(chance);
	}

	@Override
	public Chance findById(Integer id) {
		return chDao.getOne(id);
	}

	@Override
	public void delelteById(Integer id) {
		chDao.deleteById(id);
	}

}
