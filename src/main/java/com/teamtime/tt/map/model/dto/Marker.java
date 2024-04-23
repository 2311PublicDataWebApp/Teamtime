package com.teamtime.tt.map.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Marker {
	@NonNull
	private String userId;
	@NonNull
	private Timestamp markerDate;
	@NonNull
	private Double markerLat;
	@NonNull
	private Double markerLng;
	private Integer markerLike;
	private String markerAddr;
}
