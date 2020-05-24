package com.briup.service;
/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月5日 下午10:47:24 
* 类说明 
*/

import org.springframework.data.domain.Page;

import com.briup.bean.Linkman;

public interface ILinkmanService {
	//查询所有的联系人
	Page<Linkman> findAllLinkman(Integer cusId);
	//进行分页
	Page<Linkman> findAllLinkman(Integer cusId,Integer pageIndex);
	//保存联系人
	void saveLinkman(Linkman linkman);
	//通过Id查询
	Linkman findById(Integer id);
	//通过id删除
	void deleteById(Integer id);
	
	

}
