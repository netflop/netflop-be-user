package netflop.be.user.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String status;
    private String type;
    private String created_by;
    private String created_at;
    private String updated_by;
    private String updated_at;
    private Boolean is_deleted;

    public User(String id, String email, String first_name, String last_name, String phone_number, String status, String type, String created_by, String created_at, String updated_by, String updated_at, Boolean is_deleted) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.status = status;
        this.type = type;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
    }
}
