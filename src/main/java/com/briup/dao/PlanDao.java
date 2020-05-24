package com.briup.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.bean.Chance;
import com.briup.bean.Plan;

/** 
* @author 作者 angel: 
* @version 创建时间：2020年4月1日 下午10:25:29 
* 类说明 
*/
public interface PlanDao  extends JpaRepository<Plan, Integer>{
	List<Plan> findByChance(Chance chance);
}
