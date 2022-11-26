package com.garciajuanjo.RegistroUsuariosSpring;

import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import com.garciajuanjo.RegistroUsuariosSpring.service.UserAppService;

import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistroUsuariosSpringApplicationTests {

	@Autowired
	@Qualifier("userAppServiceImpl")
	private UserAppService userAppService;

	@Test
	void isSuperAdminRegister() {

		String nameAdmin = "admin";
		UserApp admin = userAppService.findByUsername(nameAdmin);

		Assert.notNull(admin, "El admin no existe y es null");
	}

}
