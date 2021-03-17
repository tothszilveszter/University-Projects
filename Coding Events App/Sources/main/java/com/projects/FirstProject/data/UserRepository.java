package com.projects.FirstProject.data;

import com.projects.FirstProject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {
}
