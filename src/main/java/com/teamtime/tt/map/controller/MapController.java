package com.teamtime.tt.map.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamtime.tt.map.model.dto.Marker;
import com.teamtime.tt.map.model.service.MapService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {
	
	private final MapService mService;
//	private final ObjectMapper objectMapper;
	
	@GetMapping("/map.do")
	public String showMap(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		try {
			String userId = userDetails.getUsername();
			List<Marker> mList = mService.selectMarkerList();
			if (userId != null && mList != null) {
				model.addAttribute("mList", mList);
				model.addAttribute("userId", userId);
				return "/map/map";
			} else {
				model.addAttribute("msg", "No Data found");
				return "/common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/errorPage";
		}
	}
	
	@ResponseBody
	@GetMapping("/list.do")
	public List<Marker> showMarkerList() {
		List<Marker> mList = mService.selectMarkerList();
		return mList;
	}
	
	@ResponseBody
	@PostMapping("/insertMarker.do")
	public String insertMarker(String userId, Double markerLat, Double markerLng, Model model) {
		Marker marker = new Marker();
		marker.setUserId(userId);
		marker.setMarkerLat(markerLat);
		marker.setMarkerLng(markerLng);
		marker.setMarkerLike(0);
		int result =  mService.insertMarker(marker);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
