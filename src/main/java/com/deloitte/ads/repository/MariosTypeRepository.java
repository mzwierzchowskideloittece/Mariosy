package com.deloitte.ads.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MariosTypeRepository extends CrudRepository<MariosType, Long> {

    Optional<MariosType> findByExternalId(UUID externalId);

}
