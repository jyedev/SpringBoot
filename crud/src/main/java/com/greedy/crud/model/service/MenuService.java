package com.greedy.crud.model.service;

import java.util.List;

import com.greedy.crud.model.dto.CategoryDTO;
import com.greedy.crud.model.dto.MenuDTO;

public interface MenuService {

	List<MenuDTO> findAllMenu();

	List<CategoryDTO> findAllCategory();

	boolean registMenu(MenuDTO menu) throws Exception;

}
