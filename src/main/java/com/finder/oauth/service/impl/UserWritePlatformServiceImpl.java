package com.finder.oauth.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finder.oauth.domain.Authority;
import com.finder.oauth.domain.User;
import com.finder.oauth.domain.UserAuthority;
import com.finder.oauth.domain.UserProfile;
import com.finder.oauth.dto.GrantedAuthorityDTO;
import com.finder.oauth.dto.UserDTO;
import com.finder.oauth.repository.UserRepository;
import com.finder.oauth.service.UserWritePlatformService;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/43/03
 */
@Service
@Transactional
public class UserWritePlatformServiceImpl implements UserWritePlatformService {

	private final UserRepository userRepo;
	private final PasswordEncoder bcryptEncoder;

	@Autowired
	public UserWritePlatformServiceImpl(final UserRepository userRepo, final PasswordEncoder bcryptEncoder) {
		this.userRepo = userRepo;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User authUser = this.userRepo.findByUsername(username);

		if (authUser != null) {
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for (UserAuthority authority : authUser.getUserAuthorities()) {
				authorities.add(new GrantedAuthorityDTO(authority.getAuthority().getUserName()));
			}
			///authUser.getAuthorities()
			
//			UserDetialDTO userDetail = new UserDetialDTO();
//			userDetail.setUserName(authUser.getUserName());
//			userDetail.setPassword(authUser.getPassword());
//			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//			for (UserAuthority authority : authUser.getUserAuthorities()) {
//				authorities.add(new GrantedAuthorityDTO(authority.getAuthority().getUserName()));
//			}
//			userDetail.setGrantedAuthorities(authorities);
			
			return authUser;
		}

		throw new UsernameNotFoundException(username);
	}

	@Override
	public Long save(UserDTO dto) {

		User user = new User();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPhoneNo(dto.getPhoneNo());
		user.setPassword(this.bcryptEncoder.encode(dto.getPassword()));
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		user.setCredentialsExpired(false);
		user.setEnabled(true);
		user.setCreatedAt(new Date());
		user.setCreatedBy(dto.getCreatedBy());
		user.setUpdatedAt(new Date());
		user.setUpdatedBy(dto.getUpdatedBy());

		Authority authority = new Authority();
		authority.setUserName(dto.getUserName());
		authority.setAuthority("ROLE_USER");

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setAuthority(authority);
		userAuthority.setUser(user);
		Set<UserAuthority> userAuthorities = new HashSet<>();
		userAuthorities.add(userAuthority);
		user.setUserAuthorities(userAuthorities);
		
		UserProfile profile = new UserProfile();
		profile.setFirstName(dto.getFirstName());
		profile.setLastName(dto.getLastName());
		profile.setAvatar(dto.getAvatar());
		profile.setBirthday(dto.getBirthday());
		profile.setGender(dto.getGender());
		profile.setCreatedAt(new Date());
		profile.setCreatedBy(dto.getCreatedBy());
		profile.setUpdatedAt(new Date());
		profile.setUpdatedBy(dto.getUpdatedBy());
		profile.setAddress(dto.getAddress());
		user.setProfile(profile);

		User usr = this.userRepo.save(user);

		return usr.getId();
	}

	@Override
	public int update(Long id, UserDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
