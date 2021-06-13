package com.harry.sso.app.domain.mapping;

import com.harry.sso.api.dto.UserDTO;
import com.harry.sso.app.domain.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserStruct {

  UserStruct INSTANCE = Mappers.getMapper(UserStruct.class);

  UserDTO map2DTO(UserEntity entity);

  List<UserDTO> map2DTOs(List<UserEntity> entities);

  UserEntity map2Entity(UserDTO dto);
}
