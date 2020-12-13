package com.geeksheet.test.witchsaga.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geeksheet.test.witchsaga.payload.Villager;

@RestController
@RequestMapping("/witchSaga")
public class StartGameController {
	final String ERROR_RESPON = "-1";
	
	@RequestMapping(value = "/start", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String startWitchSaga(@RequestBody List<Villager> villagers) {
		int yearDeath = 0;
		List<Integer> ydList = new ArrayList<Integer>();
		
		for (Villager vill : villagers) {
			if (vill.getYearOfDeath() <= 0 || vill.getAgeOfDeath() <=0 ) {
				return ERROR_RESPON;
			}
			
			yearDeath = vill.getYearOfDeath() - vill.getAgeOfDeath();
			int yearOfDeathRes = fibonacci(yearDeath);
			
			ydList.add(yearOfDeathRes);
		}
			
		
		double average = (double) ydList.stream().reduce(0, Integer::sum) / (double)ydList.size();
		
		return String.valueOf(average);
	}
	
	static int fibonacci(int num) {
		int n = num + 1;
		List<Integer> integers = new ArrayList<Integer>();
        long fib[] = new long[n];
         
        fib[0] = 0;
        fib[1] = 1;
         
        for(int i = 2; i < n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
         
        for (int i = 1; i < n; i++) {
        	integers.add((int) fib[i]);
        }
        
        Integer res = integers.stream()
                .reduce(0, Integer::sum);
		return res;
	}
}
