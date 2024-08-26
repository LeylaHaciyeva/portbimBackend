package az.code.portbim.service;
import az.code.portbim.model.About;
//import az.code.portbim.model.FileEntity;
import az.code.portbim.repository.AboutRepository;
//import az.code.portbim.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AboutService {
    private final AboutRepository aboutRepository;
//    private final FileRepository fileRepository;
    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
//        this.fileRepository = fileRepository;
    }
    @Transactional
    public void uploadFiles(String language, MultipartFile mainImage, String header
                             ,String descHeader,String desc1, String desc2, String toolHeader,
                             MultipartFile[] toolImages,
                             String clientHeader,
                             MultipartFile[] clientImages, MultipartFile descImage1, MultipartFile descImage2,
                             String descBetweenHeader, String descBetweenDescription
                             ) throws IOException {
        About about = new About();
        about.setLanguage(language);
        about.setMainImage(mainImage.getBytes());
        about.setHeader(header);
        about.setToolHeader(toolHeader);
        about.setClientHeader(clientHeader);
        about.setDesc1(desc1);
        about.setDesc2(desc2);
        about.setDescHeader(descHeader);
        about.setClientHeader(clientHeader);
        List<byte[]> clientImagesFile = new ArrayList<>();
        for (MultipartFile file : clientImages) {
            clientImagesFile.add(file.getBytes());
        }
        about.setClientImages(clientImagesFile);
        about.setDescImage1(descImage1.getBytes());
        about.setDescImage2(descImage2.getBytes());
        about.setDescBetweenHeader(descBetweenHeader);
        about.setDescBetweenDescription(descBetweenDescription);
        List<byte[]> toolImagesFile = new ArrayList<>();
                for (MultipartFile file : toolImages) {
                    toolImagesFile.add(file.getBytes());
                }
        about.setToolImages(toolImagesFile);
            aboutRepository.save(about);
    }

    public Optional<About> getAboutByLanguage(String language) {
        return aboutRepository.findByLanguage(language);
    }
}
