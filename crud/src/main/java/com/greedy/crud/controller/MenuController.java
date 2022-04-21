package com.greedy.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.crud.model.dto.MenuDTO;
import com.greedy.crud.model.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("list")
	public ModelAndView findMenuList(ModelAndView mv) {
		
		List<MenuDTO> menuList = menuService.findAllMenu();
		
		mv.addObject("menuList", menuList);
		mv.setViewName("menu/list");
		
		return mv;
	}
	
	
}
