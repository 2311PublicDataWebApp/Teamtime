package com.teamtime.tt.todo.model.service.logic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.teamtime.tt.todo.model.dto.Todo;
import com.teamtime.tt.todo.model.mapper.TodoMapper;
import com.teamtime.tt.todo.model.service.TodoService;

@Service
public class TodoServiceLogic implements TodoService{

	private TodoMapper tMapper;
	
	public TodoServiceLogic(TodoMapper tMapper) {
		this.tMapper = tMapper;
	}
	
	@Override
	public int insertTodo(Todo todo) {
		int result = tMapper.insertTodo(todo);
		return result;
	}

	@Override
	public List<Todo> selectTodoById(String userId) {
		List<Todo> tList = tMapper.selectTodoById(userId);
		return tList;
	}

	@Override
	public int deleteTodoByNo(Integer todoNo) {
		int result = tMapper.deleteTodoByNo(todoNo);
		return result;
	}

	@Override
	public Integer updateStatus(Map<String, Object> paramMap) {
	    Integer result = tMapper.updateStatus(paramMap);
	    return result;
	}
}
