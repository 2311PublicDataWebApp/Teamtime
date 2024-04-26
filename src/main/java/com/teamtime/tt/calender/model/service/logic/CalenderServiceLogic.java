package com.teamtime.tt.calender.model.service.logic;

import org.springframework.stereotype.Service;

import com.teamtime.tt.calender.model.mapper.CalenderMapper;
import com.teamtime.tt.calender.model.service.CalenderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalenderServiceLogic implements CalenderService{
	
	private final CalenderMapper cMapper;
}
