package az.code.portbim.controller;

import az.code.portbim.model.About;
import az.code.portbim.model.FileEntity;
import az.code.portbim.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//package az.code.portbim.controller;
//
//import az.code.portbim.model.About;
//import az.code.portbim.service.AboutService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
////package az.code.portbim.controller;
////import az.code.portbim.model.About;
////import az.code.portbim.service.AboutService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////
////import java.io.IOException;
////import java.util.List;
////import java.util.Optional;
////
////@RestController
////@RequestMapping(value ="/api/about" , method = {RequestMethod.GET, RequestMethod.POST})
////public class AboutController {
////    private final AboutService aboutService;
////
////    @Autowired
////    public AboutController(AboutService aboutService) {
////        this.aboutService = aboutService;
////    }
////
////    @PostMapping
////    public ResponseEntity<About> uploadAbout(
////            @RequestParam("header") String header,
////            @RequestParam("language") String language,
////            @RequestParam("files") List<MultipartFile> files) {
////        try {
////            List<byte[]> toolImages = files.stream()
////                    .map(file -> {
////                        try {
////                            return file.getBytes();
////                        } catch (IOException e) {
////                            throw new RuntimeException("Error reading file bytes", e);
////                        }
////                    })
////                    .toList();
////            About aboutDto = new About();
////            aboutDto.setLanguage(language);
////            aboutDto.setHeader(header);
////            aboutDto.setFiles(toolImages);
////            About about = aboutService.saveAbout(aboutDto);
////            return ResponseEntity.ok(about);
////        } catch (IOException e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        }
////    }
////
////    @GetMapping
////    public ResponseEntity<Optional<About>> getContent(@RequestParam String language) {
////        Optional<About> about = aboutService.getContent(language);
////        return ResponseEntity.ok(about);
////    }
////}
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
            List<FileEntity> aboutImages = files.stream()
                    .map(file -> {
                        try {
                            FileEntity fileEntity = new FileEntity();
                            fileEntity.setData(file.getBytes());
                            return fileEntity;
                        } catch (IOException e) {
                            throw new RuntimeException("Error reading file bytes", e);
                        }
                    })
                    .collect(Collectors.toList());

            About aboutDto = new About();
            aboutDto.setLanguage(language);
            aboutDto.setHeader(header);
            aboutDto.setImages(aboutImages);
            aboutImages.forEach(image -> image.setAbout(aboutDto));
            About about = aboutService.saveAbout(aboutDto);
            return ResponseEntity.ok(about);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

@GetMapping("/{id}")
public ResponseEntity<About> getAboutById(@PathVariable Long id) {
    Optional<About> aboutOptional = aboutService.getAboutById(id);
    return aboutOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
}
}














////import az.code.portbim.model.About;
////import az.code.portbim.model.AboutImage;
////import az.code.portbim.service.AboutService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////
////import java.io.IOException;
////import java.util.List;
////import java.util.Optional;
////import java.util.stream.Collectors;
////
////@RestController
////@RequestMapping("/api/about")
////public class AboutController {
////    private final AboutService aboutService;
////
////    @Autowired
////    public AboutController(AboutService aboutService) {
////        this.aboutService = aboutService;
////    }
////
////    @PostMapping
////    public ResponseEntity<About> uploadAbout(
////            @RequestParam("header") String header,
////            @RequestParam("language") String language,
////            @RequestParam("files") List<MultipartFile> files
////    ) {
////        try {
////            List<AboutImage> aboutImages = files.stream()
////                    .map(file -> {
////                        try {
////                            AboutImage aboutImage = new AboutImage();
////                            aboutImage.setImage(file.getBytes());
////                            return aboutImage;
////                        } catch (IOException e) {
////                            throw new RuntimeException("Error reading file bytes", e);
////                        }
////                    })
////                    .collect(Collectors.toList());
////
////            About aboutDto = new About();
////            aboutDto.setLanguage(language);
////            aboutDto.setHeader(header);
////            aboutDto.setImages(aboutImages);
////
////            aboutImages.forEach(image -> image.setAbout(aboutDto));
////
////            About about = aboutService.saveAbout(aboutDto);
////            return ResponseEntity.ok(about);
////        } catch (IOException e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////        }
////    }
////
////    @GetMapping
////    public ResponseEntity<About> getContent(@RequestParam String language) {
////        Optional<About> aboutOptional = aboutService.getAboutByLanguage(language);
////        return aboutOptional.map(ResponseEntity::ok)
////                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
////    }
////}
