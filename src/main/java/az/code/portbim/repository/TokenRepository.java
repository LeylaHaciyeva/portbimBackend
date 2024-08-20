package az.code.portbim.repository;

import java.util.List;
import java.util.Optional;

import az.code.portbim.model.Token;
import az.code.portbim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token,Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    Token save(Token authenticationToken);

    Token findByUser(User user);

//    User getUser();
}
