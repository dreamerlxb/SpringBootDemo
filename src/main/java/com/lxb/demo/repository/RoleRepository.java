package com.lxb.demo.repository;

import com.lxb.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "SELECT a.* FROM t_role AS a LEFT OUTER JOIN t_user_role AS b ON a.id = b.role_id WHERE b.user_id = :userId", nativeQuery = true)
    List<Role> findRolesByUserId(Long userId);
}
