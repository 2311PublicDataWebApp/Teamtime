package com.teamtime.tt.notice.model.service.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtime.tt.notice.model.dto.Notice;
import com.teamtime.tt.notice.model.dto.NoticePageInfo;
import com.teamtime.tt.notice.model.mapper.NoticeMapper;
import com.teamtime.tt.notice.model.service.NoticeService;

@Service
public class NoticeServiceLogic implements NoticeService{

	@Autowired
	private NoticeMapper nMapper;
	@Autowired
	private SqlSession session;
	
	// 공지목록 list
	@Override
	public List<Notice> selectNoticeList() {
		List<Notice> nList = nMapper.selectNoticeList(session);
		return nList;
	}

	// 공지등록 insert
	@Override
	public int insertNotice(Notice notice) {
		int result = nMapper.insertNotice(notice);
		return result;
	}

	// 공지상세 detail
	@Override
	public Notice selectByNoticeNo(int noticeNo) {
		Notice notice = nMapper.selectByNoticeNo(noticeNo);
		return notice;
	}

	// 공지수정 update
	@Override
	public int updateNotice(Notice notice) {
		int result = nMapper.updateNotice(notice);
		return result;
	}

	// 공지삭제 delete
	@Override
	public int noticeDelete(int noticeNo) {
		int result = nMapper.deleteNotice(noticeNo);
		return result;
	}

	@Override
	public int getTotalCount(Map<String, String> paramMap) {
		int totalCount = nMapper.selectTotalCount(paramMap);
		return totalCount;
	}

	@Override
	public List<Notice> searchNoticesByKeyword(NoticePageInfo pi, Map<String, String> paramMap) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Notice> searchList = nMapper.selectNoticesByKeyword(rowBounds, paramMap);
		return searchList;
	}

}
