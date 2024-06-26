package com.teamtime.tt.notice.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.teamtime.tt.notice.model.dto.Notice;
import com.teamtime.tt.notice.model.dto.NoticePageInfo;

@Mapper
public interface NoticeMapper {

	/**
	 * 공지목록 list
	 * @param session
	 * @return
	 */
	List<Notice> selectNoticeList(SqlSession session);

	/**
	 * 공지등록 insert
	 * @param session
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지상세 detail
	 * @param session
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
	 * 공지삭제
	 * @param noticeNo
	 * @return
	 */
	int deleteNotice(int noticeNo);

	/**
	 * selectTotalCount
	 * @param paramMap
	 * @return
	 */
	int searchTotalCount(Map<String, String> paramMap);

	/**
	 * 공지검색
	 * @param rowBounds
	 * @param paramMap
	 * @return
	 */
	List<Notice> selectNoticeByKeyword(RowBounds rowBounds, Map<String, String> paramMap);


	List<Notice> selectNoticeList(NoticePageInfo pi, RowBounds rowBounds);

	int selectTotalCount(SqlSession session);




}
