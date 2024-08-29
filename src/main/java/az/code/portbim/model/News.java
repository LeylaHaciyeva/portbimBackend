package az.code.portbim.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "news")
@Data
@Getter
@Setter
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private   Integer id;
    private byte[] mainImage;
    private   String title;
    private   String description;
    private Date postedDate;
    @PrePersist
    protected void onCreate() {
        if (postedDate == null) {
            postedDate = new Date(); // Set the current date
        }
    }
}
