package com.teamtime.tt.notice.model.service;

import java.util.List;
import java.util.Map;

import com.teamtime.tt.notice.model.dto.Notice;
import com.teamtime.tt.notice.model.dto.NoticePageInfo;

public interface NoticeService {

	/**
	 * 공지목록 list
	 * @return
	 */
	List<Notice> selectNoticeList();

	/**
	 * 공지등록 insert
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지상세 detail
	 * @param noticeNo
	 * @return
	 */
	Notice selectByNoticeNo(int noticeNo);

	/**
	 * 공지수정 update
	 * @param notice
	 * @return
	 */
	int updateNotice(Notice notice);

	/**
	 * 공지삭제 delete
	 * @param noticeNo
	 * @return
	 */
	int noticeDelete(int noticeNo);

	int getTotalCount(Map<String, String> paramMap);

	List<Notice> searchNoticesByKeyword(NoticePageInfo pi, Map<String, String> paramMap);

}
