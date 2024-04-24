package com.teamtime.tt.vote.model.service.logic;

import org.springframework.stereotype.Service;

import com.teamtime.tt.vote.model.mapper.VoteMapper;
import com.teamtime.tt.vote.model.service.VoteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteServiceLogic implements VoteService {
	
	private final VoteMapper vMapper;

}
