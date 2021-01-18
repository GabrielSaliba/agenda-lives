package com.agendalive.domain.mapper;

import com.agendalive.domain.dto.LiveDTO;
import com.agendalive.domain.entity.Live;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LiveMapper {

    Live toEntity(LiveDTO liveDTO);

    List<LiveDTO> toDtoList(List<Live> liveList);
}
