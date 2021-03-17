package com.projects.FirstProject.data;

import com.projects.FirstProject.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
