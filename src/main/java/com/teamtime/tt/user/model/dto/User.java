package com.teamtime.tt.user.model.dto;

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
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
	@NonNull
	private String userId;
	private String userPw;
	private String userName;
	@NonNull
	private String userNickname;
	@NonNull
	private String userEmail;
	private String userPhone;
	private String userGender;
	private String imageFile;
}
