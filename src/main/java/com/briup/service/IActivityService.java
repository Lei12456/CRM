package com.briup.service;

import org.springframework.data.domain.Page;

import com.briup.bean.Activity;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月6日 下午8:59:05 
* 类说明 
*/
public interface IActivityService {
	//保存记录
	void saveActivities(Activity activity);
	//通过客户Id查询,刚进去
	Page<Activity> findByCustomer(Integer cusId);
	//进行分页查询
	Page<Activity> findByCustomer(Integer cusId,Integer pageIndex);
	//通过id查询
	Activity findById(Integer id);
	//通过Id删除
	void deleteById(Integer id);
	
	
}
