package com.example.getir.statistics;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.getir.model.dto.MonthlyStatisticsDto;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class StatisticsDao  {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<MonthlyStatisticsDto> getMonthlyStatistics(Integer customerId) {
		Query query = entityManager.createQuery("select count(id),MONTH (orderDate),sum(price),sum(bookCount) from Order \r\n"
				+ "where customerId= :customerId group by MONTH (orderDate)");
		query.setParameter("customerId",customerId );
		List<MonthlyStatisticsDto> monthlyStatisticsDtoList = query.getResultList();
		return monthlyStatisticsDtoList;
	}
}
