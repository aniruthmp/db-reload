package io.pivotal.dbreload.repository;

import io.pivotal.dbreload.domain.AgtMstSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgtMstSourceRepository extends CrudRepository<AgtMstSource, String> {

    AgtMstSource getTopByAgtSourceId(String agtSourceId);
}
