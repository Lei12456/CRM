package com.briup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.briup.bean.Customer;
import com.briup.dao.CustomerDao;
import com.briup.service.ICustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {
	
	@Test
	public void contextLoads() {
		
	
	}

}
