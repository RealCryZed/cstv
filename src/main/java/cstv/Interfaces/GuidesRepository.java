package cstv.Interfaces;

import cstv.Models.Guide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuidesRepository extends MongoRepository<Guide, Long> {

    Page<Guide> findAll(Pageable pageable);
}
