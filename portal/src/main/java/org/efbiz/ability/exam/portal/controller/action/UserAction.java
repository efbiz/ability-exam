package org.efbiz.ability.exam.portal.controller.action;

import java.util.Date;
import org.efbiz.ability.exam.common.domain.exam.Message;
import org.efbiz.ability.exam.common.domain.user.User;
import org.efbiz.ability.exam.common.util.StandardPasswordEncoderForSha1;
import org.efbiz.ability.exam.portal.security.UserInfo;
import org.efbiz.ability.exam.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserAction {

  @Autowired
  private UserService userService;

  /**
   * 添加用户
   *
   * @param user 如果添加的用户为学员，必须指定groupId。如果添加的用户为教师，则groupId为任意数字
   *
   * @return
   */
  @RequestMapping(value = {"/add-user"}, method = RequestMethod.POST)
  public @ResponseBody
  Message addUser(@RequestBody User user) {
    user.setCreateTime(new Date());

    String password = user.getPassword() + "{" + user.getUserName().toLowerCase() + "}";
    PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
    String resultPassword = passwordEncoder.encode(password);
    user.setPassword(resultPassword);
    user.setEnabled(true);
    user.setCreateBy(-1);
    user.setUserName(user.getUserName().toLowerCase());
    Message message = new Message();
    try {
      userService.addUser(user, "ROLE_STUDENT", 0, userService.getRoleMap());
    } catch (Exception e) {
      // TODO Auto-generated catch block

      if (e.getMessage().contains(user.getUserName())) {
        message.setResult("duplicate-username");
        message.setMessageInfo("重复的用户名");
      } else if (e.getMessage().contains(user.getNationalId())) {
        message.setResult("duplicate-national-id");
        message.setMessageInfo("重复的身份证");
      } else if (e.getMessage().contains(user.getEmail())) {
        message.setResult("duplicate-email");
        message.setMessageInfo("重复的邮箱");
      } else if (e.getMessage().contains(user.getPhoneNum())) {
        message.setResult("duplicate-phone");
        message.setMessageInfo("重复的电话");
      } else {
        message.setResult(e.getCause().getMessage());
        e.printStackTrace();
      }
    }
    return message;
  }

  @RequestMapping(value = {"/student/update-user"}, method = RequestMethod.POST)
  public @ResponseBody
  Message updateUser(@RequestBody User user) {

    UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();

    String password = user.getPassword() + "{" + user.getUserName() + "}";
    PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
    String resultPassword = "";
    if (user.getPassword() != null) {
      resultPassword = "".equals(user.getPassword().trim()) ? "" : passwordEncoder.encode(password);
    }
    user.setPassword(resultPassword);
    user.setEnabled(true);
    Message message = new Message();
    try {
      userService.updateUser(user, null);
      userInfo.setTrueName(user.getTrueName());
      userInfo.setEmail(user.getEmail());
      userInfo.setNationalId(user.getNationalId());
      userInfo.setPhoneNum(user.getPhoneNum());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      if (e.getMessage().contains(user.getUserName())) {
        message.setResult("duplicate-username");
        message.setMessageInfo("重复的用户名");
      } else if (e.getMessage().contains(user.getNationalId())) {
        message.setResult("duplicate-national-id");
        message.setMessageInfo("重复的身份证");
      } else if (e.getMessage().contains(user.getEmail())) {
        message.setResult("duplicate-email");
        message.setMessageInfo("重复的邮箱");
      } else if (e.getMessage().contains(user.getPhoneNum())) {
        message.setResult("duplicate-phone");
        message.setMessageInfo("重复的电话");
      } else {
        message.setResult(e.getCause().getMessage());
        e.printStackTrace();
      }


    }
    return message;
  }

  @RequestMapping(value = {"/student/change-pwd"}, method = RequestMethod.POST)
  public @ResponseBody
  Message changePassword(@RequestBody User user) {
    Message message = new Message();
    UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
    try {
      String password = user.getPassword() + "{" + userInfo.getUsername() + "}";
      PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
      String resultPassword = passwordEncoder.encode(password);
      user.setPassword(resultPassword);
      user.setUserName(userInfo.getUsername());
      userService.updateUserPwd(user, null);
    } catch (Exception e) {
      e.printStackTrace();
      message.setResult(e.getClass().getName());
    }

    return message;
  }
}
