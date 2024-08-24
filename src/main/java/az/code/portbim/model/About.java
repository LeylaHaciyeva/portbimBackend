package az.code.portbim.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "about")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private   String header;
    @OneToMany(mappedBy = "about")
    private List<FileEntity> images=new ArrayList<>();
}