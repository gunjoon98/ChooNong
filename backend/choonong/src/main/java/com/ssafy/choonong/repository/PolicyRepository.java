package com.ssafy.choonong.repository;

import com.ssafy.choonong.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity , Integer> {

    PolicyEntity findByPolicyId(int policyId);
}
