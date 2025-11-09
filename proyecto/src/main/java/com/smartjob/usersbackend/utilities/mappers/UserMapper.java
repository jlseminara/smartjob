package com.smartjob.usersbackend.utilities.mappers;

import com.smartjob.usersbackend.adapter.in.web.models.AddUserPayload;
import com.smartjob.usersbackend.adapter.in.web.models.CreatedUserDTO;
import com.smartjob.usersbackend.adapter.in.web.models.TelephoneDTO;
import com.smartjob.usersbackend.adapter.out.persistence.entities.TelephoneEntity;
import com.smartjob.usersbackend.adapter.out.persistence.entities.UserEntity;
import com.smartjob.usersbackend.core.model.Telephone;
import com.smartjob.usersbackend.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "telephonesList", expression = "java(mapTelephoneEntityToTelephone(userEntity.getTelephonesList()))")
    User fromEntityToModel(UserEntity userEntity);

    @Mapping(target = "telephonesList", expression = "java(mapTelephoneToTelephoneEntity(user.getTelephonesList()))")
    @Mapping(target = "passwordBcrypt", source = "password")
    UserEntity fromModelToEntity(User user);

    @Mapping(target = "created", source = "createdAt")
    CreatedUserDTO fromModelToCreatedUserDTO(User user);

    @Mapping(target = "telephonesList", expression = "java(mapTelephoneDtoToTelephone(addUserPayload.getPhones()))")
    User fromAddUserPayloadToModel(AddUserPayload addUserPayload);


    default List<Telephone> mapTelephoneEntityToTelephone(List<TelephoneEntity> telephoneEntityList) {
        return telephoneEntityList.stream()
                .map(TelephoneMapper.INSTANCE::fromEntityToModel)
                .toList();
    }

    default List<TelephoneEntity> mapTelephoneToTelephoneEntity(List<Telephone> telephoneList) {
        return telephoneList.stream()
                .map(TelephoneMapper.INSTANCE::fromModelToEntity)
                .toList();
    }

    default List<Telephone> mapTelephoneDtoToTelephone(List<TelephoneDTO> telephoneDtoList) {
        return telephoneDtoList.stream()
                .map(TelephoneMapper.INSTANCE::fromDtoToModel)
                .toList();
    }

}
