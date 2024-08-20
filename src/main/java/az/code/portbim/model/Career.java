package az.code.portbim.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
    private   String responsibilities;
    private   String requiments;
    public   Date lastdate;
}
