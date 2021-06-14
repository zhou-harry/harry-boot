package com.harry.sso.web.domain.mapping;

import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.web.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserStruct {

  UserStruct INSTANCE = Mappers.getMapper(UserStruct.class);

  UserDTO map2DTO(UserEntity entity);

  List<UserDTO> map2DTOs(List<UserEntity> entities);

  UserEntity map2Entity(UserDTO dto);
}
