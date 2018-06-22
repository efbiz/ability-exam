package org.efbiz.ability.exam.portal.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.efbiz.ability.exam.common.domain.user.Role;
import org.efbiz.ability.exam.common.util.MenuItem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends User {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private int userid;
  private List<Role> roleList;
  private String trueName;
  private String rolesName;
  private String enabled;
  private int fieldId;
  private String fieldName;
  private String email;
  private int depId;
  private String nationalId;
  private String phoneNum;
  private Date lastLoginTime;
  private Date loginTime;
  private LinkedHashMap<String, MenuItem> menuMap;
  private HashMap<String, Role> roleMap;
  //保存考试历史id
  private int histId;
  //保存考试id
  private int examId;
  //保存试卷id
  private int examPaperId;


  public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
  }

}
