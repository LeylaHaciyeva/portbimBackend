package az.code.portbim.repository;

import az.code.portbim.model.About;
import az.code.portbim.model.Career;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository  extends JpaRepository<Career, Long> {

    Optional<List<Career>> findByLanguage(String language);
    @NotNull
    Optional<Career> findById(@NotNull Long id);

}
