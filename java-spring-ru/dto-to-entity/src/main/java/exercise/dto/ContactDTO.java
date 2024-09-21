package exercise.dto;

import exercise.model.Contact;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ContactDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public static ContactDTO getInstance(Contact contact) {
        var dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setPhone(contact.getPhone());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        return dto;
    }
}
