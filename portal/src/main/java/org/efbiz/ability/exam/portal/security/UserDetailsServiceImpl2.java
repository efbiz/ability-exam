package org.efbiz.ability.exam.portal.security;

import java.util.List;
import org.efbiz.ability.exam.common.domain.user.User;
import org.efbiz.ability.exam.portal.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


@Repository("userDetailsServiceImpl2")
public class UserDetailsServiceImpl2 implements UserDetailsService {

  @Autowired
  public UserMapper userMapper;
  private UserInfo userInfo;

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    // TODO Auto-generated method stub

    User user = userMapper.getUserByName(username);
    if (user == null) {
      throw new UsernameNotFoundException("user not found!");
    }
    //roles=角色代码
    List<GrantedAuthority> authorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(user.getRoles());
    userInfo = new UserInfo(username, "", user.isEnabled(), true, true, true, authorities);
    userInfo.setUserid(user.getUserId());
    userInfo.setRolesName(user.getRoles());
    userInfo.setTrueName(user.getTrueName());
    userInfo.setEmail(user.getEmail());
    userInfo.setPhoneNum(user.getPhoneNum());
    userInfo.setNationalId(user.getNationalId());
    userInfo.setDepId(user.getDepId());
    return userInfo;
  }
}
