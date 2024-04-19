package com.teamtime.tt.map.model.service.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamtime.tt.map.model.dto.Marker;
import com.teamtime.tt.map.model.mapper.MapMapper;
import com.teamtime.tt.map.model.service.MapService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapServiceLogic implements MapService {

	private final MapMapper mMapper;

	@Override
	public List<Marker> selectMarkerList() {
		List<Marker> mList = mMapper.selectMarkerList();
		return mList;
	}
}
