package com.example.getir.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.getir.model.dto.MonthlyStatisticsDto;


@RestController
public class StatisticsController {
	
	@Autowired
	StatisticsService statisticsService;
	
	@GetMapping("/monthly-statistics/{customerId}")
	private List<MonthlyStatisticsDto> getMonthlyStatistics(@PathVariable Integer customerId) {
		return statisticsService.getMonthlyStatistics(customerId) ;
	}

}
