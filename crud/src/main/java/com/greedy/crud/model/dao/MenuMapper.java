package com.greedy.crud.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greedy.crud.model.dto.MenuDTO;

@Mapper
public interface MenuMapper {

	List<MenuDTO> findAllMenu();

}
