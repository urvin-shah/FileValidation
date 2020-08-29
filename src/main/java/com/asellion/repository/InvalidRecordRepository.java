package com.asellion.repository;

import com.asellion.entity.InvalidRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidRecordRepository extends JpaRepository<InvalidRecord,Long> {
}
