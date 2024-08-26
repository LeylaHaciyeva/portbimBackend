//package az.code.portbim.model;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.*;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="tokens")
//public class Token {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    @Column(unique = true)
//    private String token;
//
//    @Enumerated(EnumType.STRING)
//    private TokenType tokenType = TokenType.BEARER;
//
//    private boolean revoked;
//
//    private boolean expired;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}
