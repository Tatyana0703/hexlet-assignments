package exercise.dto;

import exercise.model.Contact;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactCreateDTO {
    private String firstName;
    private String lastName;
    private String phone;

    public Contact toEntity() {
        var contact = new Contact();
        contact.setFirstName(this.getFirstName());
        contact.setLastName(this.getLastName());
        contact.setPhone(this.getPhone());
        return contact;
    }
}
