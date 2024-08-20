package az.code.portbim.service;
import az.code.portbim.model.About;
import az.code.portbim.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AboutService {
    private final AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public About saveAbout(About aboutDto) throws IOException {
        return aboutRepository.save(aboutDto);
    }

    public Optional<About> getContent(Long id) {
        return  aboutRepository.findById(id);
    }
}
