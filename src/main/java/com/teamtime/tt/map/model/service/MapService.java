package com.teamtime.tt.map.model.service;

import java.util.List;

import com.teamtime.tt.map.model.dto.Marker;

public interface MapService {

	/**
	 * 마커 목록 조회
	 * @return List<Marker>
	 */
	List<Marker> selectMarkerList();

}
