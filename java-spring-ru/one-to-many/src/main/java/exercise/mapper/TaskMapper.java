package exercise.mapper;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        // Подключение JsonNullableMapper
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    //автоматически ищет и подставляет entity за счет ReferenceMapper
    @Mapping(target = "assignee", source = "assigneeId")
    public abstract Task map(TaskCreateDTO dto);

    //правильно работает при source = "assignee.id"
    @Mapping(source = "assignee.id", target = "assigneeId")
    public abstract TaskDTO map(Task model);

    //автоматически ищет и подставляет entity за счет ReferenceMapper
    @Mapping(target = "assignee", source = "assigneeId")
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task model);
}
