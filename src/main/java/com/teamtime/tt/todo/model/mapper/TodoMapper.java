package com.teamtime.tt.todo.model.mapper;

import java.util.List;
import java.util.Map;

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

	/**
	 * 투두 삭제
	 * @param todoNo
	 * @return int
	 */
	int deleteTodoByNo(Integer todoNo);

	/**
	 * 투두 상태 업데이트
	 * @param status
	 * @param todoNo
	 * @return Integer
	 */
	Integer updateStatus(Map<String, Object> paramMap);

	/**
	 * 투두 상세 조회 
	 * @param todoNo
	 * @return
	 */
	Todo selectTodo(Integer todoNo);
}
