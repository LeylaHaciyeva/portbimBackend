package az.code.portbim.service;

import az.code.portbim.model.Career;
import az.code.portbim.repository.CareerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService {
    private static final Logger log = LoggerFactory.getLogger(CareerService.class);
    private final CareerRepository careerRepository;

    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    public void addVacancy(String responsibilities,
                           String language,
                           String department,
                           String type,
                           String location,
                           String skills,
                           String position,
                           Date lastDate) {
        Career career =new Career();
        career.setLanguage(language);
        career.setResponsibilities(responsibilities);
        career.setDepartment(department);
        career.setLocation(location);
        career.setType(type);
        career.setPosition(position);
        career.setSkills(skills);
        career.setLastDate(lastDate);
        careerRepository.save(career);
        ResponseEntity.ok("Career uploaded successfully!");
    }
    public Optional<List<Career>> getAllVacancyByLanguage(String language) {
        return careerRepository.findByLanguage(language);
    }
    public Optional<List<Career>> getAllVacancy() {
        return Optional.of(careerRepository.findAll());
    }
    public Optional<Career> getVacancyById(Long id) {
        return careerRepository.findById(id);
    }
    public boolean deleteVacancyById(Long id) {
     Optional<Career> deleted= careerRepository.findById(id);
        System.out.println("deleted find "+deleted);
        careerRepository.deleteById(id);
        return true;
    }
}
