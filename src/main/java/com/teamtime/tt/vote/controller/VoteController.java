package com.teamtime.tt.vote.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamtime.tt.alarm.model.dto.Alarm;
import com.teamtime.tt.alarm.model.service.AlarmService;
import com.teamtime.tt.common.PageInfo;
import com.teamtime.tt.team.model.dto.Team;
import com.teamtime.tt.team.model.service.TeamService;
import com.teamtime.tt.user.model.dto.User;
import com.teamtime.tt.user.model.service.UserService;
import com.teamtime.tt.vote.model.dto.Vote;
import com.teamtime.tt.vote.model.dto.VoteOption;
import com.teamtime.tt.vote.model.dto.VoteResult;
import com.teamtime.tt.vote.model.service.VoteService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {
	
	private final VoteService vService;
	private final UserService uService;
	private final AlarmService aService;
	private final TeamService tService;
	
	@GetMapping("/list.do")
	public String showVoteList(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, @AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, Integer teamNo) {
		try {
			if (session.getAttribute("teamNo") != null) {
				if (session.getAttribute("teamNo") != teamNo) {
					currentPage = 1;
				}
			}
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Team> tList = tService.selectTeamById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			Integer totalCount = vService.getTotalCount(teamNo);
			PageInfo pInfo = new PageInfo(currentPage, totalCount, 10);
			List<Vote> vList = vService.selectVoteList(pInfo, teamNo);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			session.setAttribute("tList", tList);
			session.setAttribute("teamNo", teamNo);
			if (totalCount > 0) {
				model.addAttribute("teamNo", teamNo);
			}
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("vList", vList);
			return "/vote/voteList";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@GetMapping("/detail.do")
	public String showVoteDetail(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, Integer voteNo
			, Integer teamNo) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			List<Team> tList = tService.selectTeamById(userId);
			model.addAttribute("user", user);
			session.setAttribute("tList", tList);
			session.setAttribute("aList", aList);
			
			Vote vote = vService.selectVoteByNo(voteNo);
			if (vote != null) {
				List<VoteOption> voList = vService.selectVoteOptionList(voteNo);
				if (voList != null) {
					model.addAttribute("vote", vote);
					model.addAttribute("voList", voList);
					return "/vote/voteDetail";
				} else {
					model.addAttribute("msg", "투표 항목이 없어요..");
					return "/common/error";
				}
			} else {
				model.addAttribute("msg", "투표가 없어요..");
				return "/common/error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@GetMapping("/insert.do")
	public String showInsertVote(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, Integer teamNo) {
		String userId = userDetails.getUsername();
		User user = uService.selectUserById(userId);
		List<Team> tList = tService.selectTeamById(userId);
		List<Alarm> aList = aService.selectUnreadAlarm(userId);
		model.addAttribute("user", user);
		session.setAttribute("aList", aList);
		session.setAttribute("tList", tList);
		model.addAttribute("teamNo", teamNo);
		return "/vote/insertVote";
	}
	
	@PostMapping("/insert.do")
	public String insertVote(@AuthenticationPrincipal UserDetails userDetails
			, HttpSession session
			, Model model
			, Vote vote
			, String[] voteOptions
			, Integer teamNo
			, RedirectAttributes ra) {
		try {
			String userId = userDetails.getUsername();
			User user = uService.selectUserById(userId);
			List<Team> tList = tService.selectTeamById(userId);
			List<Alarm> aList = aService.selectUnreadAlarm(userId);
			model.addAttribute("user", user);
			session.setAttribute("aList", aList);
			session.setAttribute("tList", tList);
			model.addAttribute("teamNo", teamNo);
			vote.setTeamNo(teamNo);
			int result = vService.insertVote(vote);
			if (result > 0 && voteOptions != null) {
				VoteOption vOption = new VoteOption();
				for (String voteOptionContent : voteOptions) {
					vOption.setVoteOptionContent(voteOptionContent);
					result = vService.insertVoteOption(vOption);
				}
			}
			ra.addAttribute("teamNo", teamNo);
			return "redirect:/vote/list.do";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "/common/error";
		}
	}
	
	@ResponseBody
	@PostMapping("/submit.do")
	public String insertVoteResult(@RequestParam(value="checkedOptions[]") List<String> checkedOptions
			, @AuthenticationPrincipal UserDetails userDetails) {
//		System.out.println(checkedOptions);
		String userId = userDetails.getUsername();
		int result = 0;
		
		// 초기화
//		result = vService.deleteVoteResult();
		
		for (String checkedOption : checkedOptions) {
			VoteResult voteResult = new VoteResult(Integer.parseInt(checkedOption), userId);
			result = vService.insertVoteResult(voteResult);			
		}
		if (result > 0) {
			
		}
		return "";
	}

}
