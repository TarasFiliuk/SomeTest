package com.kindgeek.test.service;

import com.kindgeek.test.entity.Project;
import com.kindgeek.test.service.request.ProjectRequest;

public interface ProjectService {
    Project createProject();
    Project getProject(int projectId);
    void deleteProject(int projectId);
    Project updateProject(int projectId, ProjectRequest projectRequest);
}
