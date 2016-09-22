package org.springside.examples.quickstart.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.JobInfo;
import org.springside.examples.quickstart.repository.JobInfoDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class JobService {

	@Autowired
	private JobInfoDao jobInfoDao;

	public Page<JobInfo> getJobs(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<JobInfo> spec = buildSpecification(userId, searchParams);

		return jobInfoDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("titile".equals(sortType)) {
			sort = new Sort(Direction.DESC, "titile");
		} else if ("createDate".equals(sortType)) {
			sort = new Sort(Direction.DESC, "createDate");
		} else if ("jobxz".equals(sortType)) {
			sort = new Sort(Direction.DESC, "jobxz");
		} else if ("salary".equals(sortType)) {
			sort = new Sort(Direction.DESC, "salary");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<JobInfo> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<JobInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), JobInfo.class);
		return spec;
	}
}
