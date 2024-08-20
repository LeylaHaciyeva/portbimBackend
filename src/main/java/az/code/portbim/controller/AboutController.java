package az.code.portbim.controller;

import az.code.portbim.model.About;
import az.code.portbim.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//package az.code.portbim.controller;
//import az.code.portbim.model.About;
//import az.code.portbim.service.AboutService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value ="/api/about" , method = {RequestMethod.GET, RequestMethod.POST})
//public class AboutController {
//    private final AboutService aboutService;
//
//    @Autowired
//    public AboutController(AboutService aboutService) {
//        this.aboutService = aboutService;
//    }
//
//    @PostMapping
//    public ResponseEntity<About> uploadAbout(
//            @RequestParam("header") String header,
//            @RequestParam("language") String language,
//            @RequestParam("files") List<MultipartFile> files) {
//        try {
//            List<byte[]> toolImages = files.stream()
//                    .map(file -> {
//                        try {
//                            return file.getBytes();
//                        } catch (IOException e) {
//                            throw new RuntimeException("Error reading file bytes", e);
//                        }
//                    })
//                    .toList();
//            About aboutDto = new About();
//            aboutDto.setLanguage(language);
//            aboutDto.setHeader(header);
//            aboutDto.setFiles(toolImages);
//            About about = aboutService.saveAbout(aboutDto);
//            return ResponseEntity.ok(about);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<Optional<About>> getContent(@RequestParam String language) {
//        Optional<About> about = aboutService.getContent(language);
//        return ResponseEntity.ok(about);
//    }
//}
@RestController
@RequestMapping("/api/about")
public class AboutController {
    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @PostMapping
    public ResponseEntity<About> uploadAbout(
            @RequestParam("header") String header,
            @RequestParam("language") String language,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            List<byte[]> toolImages = files.stream()
                    .map(file -> {
                        try {
                            return file.getBytes();
                        } catch (IOException e) {
                            throw new RuntimeException("Error reading file bytes", e);
                        }
                    })
                    .toList();
            About aboutDto = new About();
            aboutDto.setLanguage(language);
            aboutDto.setHeader(header);
            aboutDto.setFiles(toolImages);
            About about = aboutService.saveAbout(aboutDto);
            return ResponseEntity.ok(about);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<Optional<About>> getContent(@RequestParam String id) {
        Optional<About> about = aboutService.getContent(Long.valueOf(id));
        return ResponseEntity.ok(about);
    }
}
