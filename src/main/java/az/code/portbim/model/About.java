package az.code.portbim.model;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "about")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private   String header;

//    private   String title;
//    private   String desc1;
//    private   String desc2;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private   byte[] mainImage;
//        @Column(columnDefinition = "jsonb")
//        @Type(type = "jsonb")
    @Lob
    @ElementCollection // To handle a collection of byte arrays
    @CollectionTable(name = "about_images", joinColumns = @JoinColumn(name = "about_id"))
    @Column(name = "image")
    private List<byte[]> files;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private byte[] image;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private   byte[] clientImages;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private   byte[] aboutImage2;
//    @Lob
//    @Column(columnDefinition = "BYTEA")
//    private   byte[] aboutImage3;
//    private   String betweenTextTitle;
//    private   String betweenTextDescription;

}


