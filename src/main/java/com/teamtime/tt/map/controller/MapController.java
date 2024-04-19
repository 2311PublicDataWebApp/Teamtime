package com.teamtime.tt.map.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtime.tt.map.model.dto.Marker;
import com.teamtime.tt.map.model.service.MapService;
import com.teamtime.tt.user.model.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {
	
	private final UserService uService;
	private final MapService mService;
	private final ObjectMapper objectMapper;
	
	@GetMapping("/map.do")
	public String showMap(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		try {
			String userId = userDetails.getUsername();
			List<Marker> mList = mService.selectMarkerList();
			if (userId != null && mList != null) {
				model.addAttribute("mList", mList);
//				String mListToJson = objectMapper.writeValueAsString(mList);
//				model.addAttribute("mList", mListToJson);
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
}
