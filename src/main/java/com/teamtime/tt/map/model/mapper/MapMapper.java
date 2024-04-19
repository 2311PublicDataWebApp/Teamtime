package com.teamtime.tt.map.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.teamtime.tt.map.model.dto.Marker;

@Mapper
public interface MapMapper {

	/**
	 * 마커 목록 조회
	 * @return List<Marker>
	 */
	List<Marker> selectMarkerList();

}
