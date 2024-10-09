package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private Long categoryId;
    private  String categoryName;
    private String title;
    private int price;
    private double rating;
//    private LocalDate createdAt;
    private String createdAt;
//    private LocalDate updatedAt;
    private String updatedAt;
}
