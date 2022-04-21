package com.greedy.crud.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.crud.model.dto.CategoryDTO;
import com.greedy.crud.model.dto.MenuDTO;
import com.greedy.crud.model.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

	private MenuService menuService;
	private MessageSource messageSource;

	@Autowired
	public MenuController(MenuService menuService, MessageSource messageSource) {
		this.menuService = menuService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("list")
	public ModelAndView findMenuList(ModelAndView mv) {
		
		List<MenuDTO> menuList = menuService.findAllMenu();
		
		mv.addObject("menuList", menuList);
		mv.setViewName("menu/list");
		
		return mv;
	}
	
	@GetMapping(value="category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findCategoryList(){
		return menuService.findAllCategory();
	}
	
	@GetMapping("regist")
	public void registPage() {}
	
	@PostMapping("regist")
	public String registMenu(@ModelAttribute MenuDTO menu, RedirectAttributes rttr, Locale locale) throws Exception {
		log.error("등록 요청 메뉴 : {}", menu);
		log.warn("등록 요청 메뉴 : {}", menu);
		log.info("등록 요청 메뉴 : {}", menu);
		log.debug("등록 요청 메뉴 : {}", menu);
		log.trace("등록 요청 메뉴 : {}", menu);
		
		menuService.registMenu(menu);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale));
		
		return "redirect:/menu/list";
		
		
	}
	
}
