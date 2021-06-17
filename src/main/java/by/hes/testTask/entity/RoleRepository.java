package by.hes.testTask.entity;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query(value = "FROM Role r")
    public List<Role> getRoleList();

    @Query(value = "FROM Role r WHERE r.name = :name")
    public Role getRoleByName(@Param("name") String roleName);


}

