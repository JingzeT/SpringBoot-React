/*
 * Copyright 2022 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.example.ppmtool.services;


import com.example.ppmtool.domain.Backlog;
import com.example.ppmtool.domain.Project;
import com.example.ppmtool.domain.ProjectTask;
import com.example.ppmtool.exceptions.ProjectIdException;
import com.example.ppmtool.exceptions.ProjectNotFoundException;
import com.example.ppmtool.repositories.BacklogRepository;
import com.example.ppmtool.repositories.ProjectRepository;
import com.example.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 97679
 * @date 2022/6/2 8:24
 */
@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // Exception: Project not found

        try {
            // PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
            // set the BL to PT
            projectTask.setBacklog(backlog);
            // we want to have our project sequence to be like this IDPRO-1 IDPRO-2
            Integer backlogSequence = backlog.getPTSequence();

            // Update the BL SEQUENCE
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);

            // Add sequence to project task
            projectTask.setProjectSequence(projectIdentifier.toUpperCase() + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier.toUpperCase());
            // INITIAL priority when priority null
            if (projectTask.getPriority() == null) { // In the future we need projectTask.getPriority() == 0 to handle the form
                projectTask.setPriority(3);
            }
            // INITIAL statue when status null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project Not Found");
        }

    }

    public Iterable<ProjectTask> findBacklogById(String id) {

        Project project = projectRepository.findByProjectIdentifier(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project with ID '" + id + "' doesn't exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {

        // make sure we are searching on the right backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID '" + backlog_id + "' does not exist");
        }

        // make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not found");
        }

        // make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().toUpperCase().equals(backlog_id.toUpperCase())) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exist in the '" + backlog_id + "'");
        }

        return projectTask;
    }


    // Update project task
    // find existing project task
    // replace it with updated task
    // save update
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
//        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);

    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
//        Backlog backlog = projectTask.getBacklog();
//
//        List<ProjectTask> projectTaskList = backlog.getProjectTasks();
//        projectTaskList.remove(projectTask);
//        backlogRepository.save(backlog);

        projectTaskRepository.delete(projectTask);
    }

}