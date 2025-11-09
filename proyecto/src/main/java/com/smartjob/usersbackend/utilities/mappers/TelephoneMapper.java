package com.smartjob.usersbackend.utilities.mappers;

import com.smartjob.usersbackend.adapter.in.web.models.TelephoneDTO;
import com.smartjob.usersbackend.adapter.out.persistence.entities.TelephoneEntity;
import com.smartjob.usersbackend.core.model.Telephone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TelephoneMapper {
    TelephoneMapper INSTANCE = Mappers.getMapper(TelephoneMapper.class);

    Telephone fromEntityToModel(TelephoneEntity telephoneEntity);
    TelephoneEntity fromModelToEntity(Telephone telephone);

    TelephoneDTO fromModelToDto(Telephone telephone);
    Telephone fromDtoToModel(TelephoneDTO telephoneDTO);
}
