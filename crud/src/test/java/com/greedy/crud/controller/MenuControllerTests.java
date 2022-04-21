package com.greedy.crud.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.crud.config.CrudApplication;

@SpringBootTest
@ContextConfiguration(classes = {CrudApplication.class})
public class MenuControllerTests {

	@Autowired
	private MenuController menuController;
	private MockMvc mockMvc;
	
	@Test
	public void testInit() {
		
		assertNotNull(menuController);
		assertNotNull(mockMvc);
	}
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
	}
	
	@Test
	public void 전체_메뉴_조회용_컨트롤러_테스트() throws Exception {
		
		//given
		
		//when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/menu/list"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("menu/list"))
				.andDo(MockMvcResultHandlers.print());
	}
}
