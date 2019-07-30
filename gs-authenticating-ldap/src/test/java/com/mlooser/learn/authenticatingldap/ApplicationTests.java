package com.mlooser.learn.authenticatingldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

  @Autowired
  private MockMvc mockMvc;
  
	@Test
	public void validLoginTest() throws Exception {
	  FormLoginRequestBuilder login = formLogin()
	      .user("ben")
	      .password("benspassword");
	  
	  mockMvc
	    .perform(login)
	    .andExpect(authenticated().withUsername("ben"));
	}

	@Test
	public void invalidLoginTest() throws Exception {
	  FormLoginRequestBuilder login = formLogin()
        .user("invalid")
        .password("invalid");
    
    mockMvc
      .perform(login)
      .andExpect(unauthenticated());
	}
}
