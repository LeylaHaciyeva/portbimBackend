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
    private byte[] mainImage;
    private   String header;
    @Column(length = 1000)
    private String descHeader;
    @Column(length = 1000)
    private String desc1;
    @Column(length = 1000)
    private String desc2;
    private String clientHeader;
    @ElementCollection
    private List<byte[]> clientImages;
    private byte[] descImage1;
    private byte[] descImage2;
    @Column(length = 1000)
    private String descBetweenHeader;
    @Column(length = 1000)
    private String descBetweenDescription;
    private String toolHeader;
    @ElementCollection
    private List<byte[]> toolImages;
}