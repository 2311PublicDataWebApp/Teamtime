package com.teamtime.tt.user.model.dto;

import java.io.Serializable;

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
public class User implements Serializable{
	@NonNull
	private String userId;
	private String userPw;
	private String userName;
	@NonNull
	private String userNickname;
	private String userEmail;
	private String userPhone;
	private String imageFile;
	private String userRole;
}
