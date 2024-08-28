package az.code.portbim.controller;

import az.code.portbim.model.Career;
import az.code.portbim.service.CareerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/career")
public class CareerController {
    private final CareerService careerService;
    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping
    public ResponseEntity<String> addCareer(@RequestParam String responsibilities,
                                            @RequestParam String language,
                                            @RequestParam String department,
                                            @RequestParam String location,
                                            @RequestParam String type,
                                            @RequestParam String position,
                                            @RequestParam String skills,
                                            @RequestParam("lastDate") String lastDate
//                                            @RequestParam Date postedDate

    ) throws ParseException {
        Date lastDateConverter = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            lastDateConverter = formatter.parse(lastDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        }
        System.out.println(lastDate);
        careerService.addVacancy(responsibilities,language,department,type,location,skills,position,lastDateConverter);
        return ResponseEntity.ok("Files uploaded successfully!");
    }
    @GetMapping("/{language}")
    public ResponseEntity<List<Career>> getAllVacancyByLanguage(@PathVariable String language) {
        Optional<List<Career>> careerOptional = careerService.getAllVacancyByLanguage(language);
        return careerOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @GetMapping
    public ResponseEntity<List<Career>> getAllVacancy() {
        Optional<List<Career>> careerOptional = careerService.getAllVacancy();
        return careerOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @GetMapping("/{language}/{id}")
    public ResponseEntity<Career> getVacancyById(@PathVariable String language,@PathVariable String id) {
        Optional<Career> careerOptional = careerService.getVacancyById(Long.valueOf(id));
        return careerOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable String id) {
        boolean deleted = careerService.deleteVacancyById(Long.valueOf(id));
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
