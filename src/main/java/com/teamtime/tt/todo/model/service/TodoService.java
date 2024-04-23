package com.teamtime.tt.todo.model.service;

import java.util.List;
import java.util.Map;

import com.teamtime.tt.todo.model.dto.Todo;

public interface TodoService {

	/**
	 * 투두등록
	 * @param todo
	 * @return
	 */
	int insertTodo(Todo todo);

	/**
	 * 할 일 리스트 조회
	 * @param userId
	 * @return List<Todo>
	 */
	List<Todo> selectTodoById(String userId);

	/**
	 * 투두삭제
	 * @param todoNo
	 * @return
	 */
	int deleteTodoByNo(Integer todoNo);

	/**
	 * 투두상태업데이트
	 * @param status
	 * @param todoNo
	 * @return
	 */
	Integer updateStatus(Map<String, Object> paramMap);
}
