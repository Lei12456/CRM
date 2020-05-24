package com.briup.service;

import org.springframework.data.domain.Page;

import com.briup.bean.Chance;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午2:05:54 
* 类说明 销售商机的service接口
*/
public interface IChanceService {
	Page<Chance> findAllChances(String name,String address);
	Page<Chance> findAllChances(String name,String address,Integer pageIndex);
	
	//添加
	void saveChance(Chance chance);
	//通过id查询
	Chance findById(Integer id);
	//通过id删除
	void delelteById(Integer id);
}
