package org.efbiz.ablility.exam.manager.security;

import java.util.List;
import org.efbiz.ability.exam.common.domain.user.User;
import org.efbiz.ablility.exam.manager.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  public UserMapper userMapper;
  private UserInfo userInfo;

  @Override
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
    userInfo = new UserInfo(username, user.getPassword(), true, true, true, true, authorities);
    userInfo.setUserid(user.getUserId());
    userInfo.setRolesName(user.getRoles());
    return userInfo;
  }


}
