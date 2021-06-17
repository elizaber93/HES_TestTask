package by.hes.testTask.entity;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    @Query(value = "FROM Status st")
    public List<Status> getStatusList();


    @Query(value = "FROM Status s WHERE s.name LIKE :name")
    public Status getStatusByName(@Param("name") String statusName);


}