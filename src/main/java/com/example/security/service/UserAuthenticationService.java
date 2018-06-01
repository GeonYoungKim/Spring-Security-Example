package com.example.security.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.security.model.dto.UserDTO;

public class UserAuthenticationService implements UserDetailsService{

	private SqlSessionTemplate sqlSession;
	
	public UserAuthenticationService() {
	
	}
	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		System.out.println("유저 로드");
		Map<String, Object> user=sqlSession.selectOne("user.selectUser",userid);
		System.out.println(user.toString());
		if(user==null) {
			throw new UsernameNotFoundException(userid);
		}
		System.out.println("log1");
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		System.out.println(user.get("authority").toString());
		authority.add(new SimpleGrantedAuthority(user.get("authority").toString()));
		
		return new UserDTO(user.get("username").toString(),user.get("password").toString(),
				(Integer)Integer.valueOf(user.get("enabled").toString())==1,true,true,true,authority,user.get("username").toString());
	}

}
