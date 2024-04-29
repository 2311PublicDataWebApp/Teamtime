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

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.map.model.dto.Marker;
import com.teamtime.tt.map.model.service.MapService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {
	
	private final MapService mService;
	private final UserService uService;
	private final AlarmService aService;
//	private final ObjectMapper objectMapper;
	
	@GetMapping("/map.do")
	public String showMap(@AuthenticationPrincipal UserDetails userDetails
			, Model model
			, HttpSession session
			, Integer teamNo) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			model.addAttribute("teamNo", teamNo);
			List<Marker> mList = mService.selectMarkerList(teamNo);
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
	public List<Marker> showMarkerList(Integer teamNo) {
		List<Marker> mList = mService.selectMarkerList(teamNo);
		return mList;
	}
	
	@ResponseBody
	@PostMapping("/insertMarker.do")
	public String insertMarker(String userId, Double markerLat, Double markerLng, Integer teamNo, Model model) {
		Marker marker = new Marker();
		marker.setUserId(userId);
		marker.setMarkerLat(markerLat);
		marker.setMarkerLng(markerLng);
		marker.setMarkerLike(0);
		marker.setTeamNo(teamNo);
		int result =  mService.insertMarker(marker);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
