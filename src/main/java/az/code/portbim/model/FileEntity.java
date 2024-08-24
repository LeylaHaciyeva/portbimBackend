package az.code.portbim.model;
import az.code.portbim.model.About;
import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "files")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private About about;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "filename")
    private String filename;

}
