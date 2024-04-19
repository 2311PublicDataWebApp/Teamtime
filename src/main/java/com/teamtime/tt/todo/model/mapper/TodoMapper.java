package com.teamtime.tt.todo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.todo.model.dto.Todo;

@Mapper
public interface TodoMapper {

	/**
	 * 투두 등록
	 * @param todo
	 * @return int
	 */
	int insertTodo(Todo todo);

	/**
	 * 할 일 목록 조회
	 * @param userId
	 * @return List<Todo>
	 */
	List<Todo> selectTodoById(String userId);

}
