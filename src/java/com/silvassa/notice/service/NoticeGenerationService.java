package com.silvassa.notice.service;


import org.springframework.web.servlet.ModelAndView;

import org.json.simple.JSONObject;

public interface NoticeGenerationService {
 
	public void getData(String zone_id, String ward_id, String loc_id, String prop_id, String type);
	public ModelAndView viewNotice(String taxNo);
	public JSONObject loadtTaxNoticeData(String noticeSel);
	public String generateTaxNotice(String params);
}
