package az.code.portbim.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "career")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private   Integer id;
    private   String position;
    private   String department;
    private   String type;
    private   String location;
    private   String language;
    @Column(length = 5000)
    private   String responsibilities;
    @Column(length = 5000)
    private   String skills;
    @Temporal(TemporalType.DATE)
    public   Date lastDate;
    @Temporal(TemporalType.DATE)
    @Column(updatable = false) // Ensure this field is not updated after creation
    private Date postedDate;
    @PrePersist
    protected void onCreate() {
        if (postedDate == null) {
            postedDate = new Date(); // Set the current date
        }
    }
}
