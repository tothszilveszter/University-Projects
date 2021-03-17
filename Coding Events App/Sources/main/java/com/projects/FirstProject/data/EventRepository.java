package com.projects.FirstProject.data;

import com.projects.FirstProject.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> { //<stuff stored, primary key type>

}
//crud repository automatically creates create, read, update, remove mainstream methods into a class in memory
// (like we would say class xyz implements.. )