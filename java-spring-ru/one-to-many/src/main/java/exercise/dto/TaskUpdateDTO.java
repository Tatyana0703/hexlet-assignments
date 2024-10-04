package exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class TaskUpdateDTO {
//    @NotNull
//    private JsonNullable<Long> assigneeId;
    private Long assigneeId;

//    @NotBlank
//    private JsonNullable<String> title;
    private String title;

//    @NotBlank
//    private JsonNullable<String> description;
    private String description;
}
