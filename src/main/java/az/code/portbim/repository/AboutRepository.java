package az.code.portbim.repository;

import az.code.portbim.model.About;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {
//    Optional<About> findByLanguage(String language);
    @NotNull
    Optional<About> findById(@NotNull Long id);
}