package com.example.ppmtool.repositories;

import com.example.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 97679
 * @date 2022/5/31 16:12
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String sequence);
}
