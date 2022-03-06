package com.example.getir.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getir.model.dto.MonthlyStatisticsDto;

@Service
public class StatisticsService {
	
	@Autowired
	StatisticsDao statisticsDao;

	public List<MonthlyStatisticsDto> getMonthlyStatistics(Integer customerId) {
		return statisticsDao.getMonthlyStatistics(customerId);
	}



}
