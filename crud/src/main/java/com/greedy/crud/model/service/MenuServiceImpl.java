package com.greedy.crud.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.crud.model.dao.MenuMapper;
import com.greedy.crud.model.dto.CategoryDTO;
import com.greedy.crud.model.dto.MenuDTO;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;
	
	@Autowired
	public MenuServiceImpl(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public List<MenuDTO> findAllMenu() {
		return menuMapper.findAllMenu();
	}

	@Override
	public List<CategoryDTO> findAllCategory() {
		return menuMapper.findAllCategory();
	}

	@Override
	public boolean registMenu(MenuDTO menu) throws Exception {
		
		int result = menuMapper.registMenu(menu);
		
		if(result <= 0) {
			throw new Exception("메뉴 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}

	

}
