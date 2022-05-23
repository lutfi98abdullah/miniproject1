package vttp.miniproject1.Repositories;

import vttp.miniproject1.Entity.Anime;
import vttp.miniproject1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {



}
