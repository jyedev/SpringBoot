package com.greedy.crud.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.greedy.crud.config.CrudApplication;
import com.greedy.crud.model.dto.CategoryDTO;
import com.greedy.crud.model.dto.MenuDTO;

@SpringBootTest
@ContextConfiguration(classes = {CrudApplication.class})
public class MenuServiceTests {
	
	@Autowired
	private MenuService menuService;
	
	@Test
	public void testInit() {
		
		assertNotNull(menuService);
	}
	
	@Test
	public void 전체_메뉴_조회용_서비스_메소드_테스트() {
		
		//given
		
		//when
		List<MenuDTO> menuList = menuService.findAllMenu();
		
		//then
		assertNotNull(menuList);
	}
	
	@Test
	public void 전체_카테고리_조회용_서비스_메소드_테스트() {
		
		//given
		
		//when
		List<CategoryDTO> categoryList = menuService.findAllCategory();
		
		//then
		assertNotNull(categoryList);
	}

}
