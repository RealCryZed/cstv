package cstv.Interfaces;

import cstv.Models.GuidesInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GuidesInfoRepository extends MongoRepository<GuidesInfo, Integer> {

    GuidesInfo findGuideInfoById(Integer id);

    List<GuidesInfo> findTop15ByOrderByDateOfCreationDescTimeOfCreationDesc();
}
