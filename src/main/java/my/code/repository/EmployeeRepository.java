package my.code.repository;

import my.code.entity.Employee;
import my.code.projection.EmployeeNameView;
import my.code.projection.EmployeeNativeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, EmployeeCustomRepository,
        QuerydslPredicateExecutor<Employee> {
    // findByFirstNameContainingIgnoreCase
    Optional<Employee> findByFirstNameContaining(String firstName);

    //    @Query("SELECT e FROM Employee e where e.firstName = ?1 and e.salary = ?2")
//    @Query("SELECT e FROM Employee e where e.firstName = :name and e.salary = :salary")
    @Query(value = "SELECT e.* FROM employee e where e.first_name = :name and e.salary = :salary",
            nativeQuery = true)
    List<Employee> findAllByFirstNameAndSalary(@Param("name") String firstName,
                                               @Param("salary") Integer salary);

    List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);

    @Query(value = "SELECT e.id as id, e.first_name || e.last_name as fullName " +
            "FROM employee e " +
            "where e.salary > :salary",
            nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);
}
