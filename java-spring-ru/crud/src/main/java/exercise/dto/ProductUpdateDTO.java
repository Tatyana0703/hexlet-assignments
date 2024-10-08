package exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class ProductUpdateDTO {
    @NotNull    //относится к значению под JsonNullable, т.е. либо передается в json либо не передается в json
    private JsonNullable<Long> categoryId;

    @NotBlank    //относится к значению под JsonNullable, т.е. либо передается в json либо не передается в json
    private JsonNullable<String> title;

    @NotNull    //относится к значению под JsonNullable, т.е. либо передается в json либо не передается в json
    private JsonNullable<Integer> price;
}
