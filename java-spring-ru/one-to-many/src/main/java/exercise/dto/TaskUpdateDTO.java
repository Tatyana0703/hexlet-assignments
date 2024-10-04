package exercise.dto;

import lombok.Getter;
import lombok.Setter;

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
