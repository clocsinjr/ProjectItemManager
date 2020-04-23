package com.miso.miniprojectplanner.controller;

import com.miso.miniprojectplanner.domain.ProjectItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectItemRepository extends CrudRepository<ProjectItem, Long> {
    List<ProjectItem> findByTitleAllIgnoreCase(String title);
}
