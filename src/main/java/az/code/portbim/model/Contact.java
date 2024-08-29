package az.code.portbim.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "contact")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private   Integer id;
    private   String name;
    private   String email;
    private   String phone;
    @Column(length = 2000)
    private   String message;
    private Date postedDate;
    @PrePersist
    protected void onCreate() {
        if (postedDate == null) {
            postedDate = new Date(); // Set the current date
        }
    }
}
