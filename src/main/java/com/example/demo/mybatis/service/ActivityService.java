package com.example.demo.mybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mybatis.entity.Activity;
import com.example.demo.mybatis.mapper.ActivityMapper;

@Service
public class ActivityService {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityService.class);
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	@Autowired
	private ActivityMapper activityMapper;

	public Activity getActivityById(String activityId) {
		return activityMapper.getActivityById(activityId);
	}
}
