package cstv.Interfaces;

import cstv.Models.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GuidesRepository extends MongoRepository<Guide, Integer> {

    Page<Guide> findAll(Pageable pageable);

    Guide findGuideById(Integer id);

    List<Guide> findTop15ByOrderByDateOfCreationDesc();
}
