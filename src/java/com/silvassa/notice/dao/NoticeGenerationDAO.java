package com.silvassa.notice.dao;


import com.silvassa.model.NoticeViewBean;
import org.json.simple.JSONObject;

public interface NoticeGenerationDAO {
	public NoticeViewBean getNotice(String taxNo);
	public JSONObject loadtTaxNoticeData(String noticeSel);
        public String generateTaxNotice(String params);
}
