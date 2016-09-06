package org.springside.examples.quickstart.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.JobInfo;

public interface JobInfoDao extends PagingAndSortingRepository<JobInfo, Long>, JpaSpecificationExecutor<JobInfo> {

}
