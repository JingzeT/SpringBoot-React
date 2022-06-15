package com.example.ppmtool.repositories;

import com.example.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 97679
 * @date 2022/5/22 14:50
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
