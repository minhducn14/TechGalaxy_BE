package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.entities.SystemUser;
import org.mapstruct.factory.Mappers;

public interface SystemUserMapper {
    SystemUserMapper INSTANCE = Mappers.getMapper(SystemUserMapper.class);


}
