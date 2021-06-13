package com.harry.sso.api.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

  private Long id;

  private String username;
  private String nickName;
  private boolean gender;
  private String phone;
  private String email;
  private String avatar;
  private String password;
  private String salt;
  private Date birthday;
  private boolean status;
  private String thirdId;
  private String thirdType;

}
