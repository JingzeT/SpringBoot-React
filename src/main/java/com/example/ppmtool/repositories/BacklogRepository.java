package com.example.ppmtool.repositories;

import com.example.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 97679
 * @date 2022/5/31 16:11
 */
@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);
}
