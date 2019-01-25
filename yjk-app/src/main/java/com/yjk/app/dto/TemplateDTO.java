package com.yjk.app.dto;



import java.util.Map;

public class TemplateDTO {
	
		//必填 用于获取formId
		private Long memberId;
	    private String touser;
	    private String template_id;
	    private String page;
	    private String form_id;
	    private String emphasis_keyword;
	    private String access_token;
	    private Map<String, Map<String, String>> data;
	    
	    
	    
		public Long getMemberId() {
			return memberId;
		}
		public void setMemberId(Long memberId) {
			this.memberId = memberId;
		}
		public Map<String, Map<String, String>> getData() {
			return data;
		}
		public void setData(Map<String, Map<String, String>> data) {
			this.data = data;
		}
		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public void setTouser(String touser) {
	         this.touser = touser;
	     }
	     public String getTouser() {
	         return touser;
	     }

	    public void setTemplate_id(String template_id) {
	         this.template_id = template_id;
	     }
	     public String getTemplate_id() {
	         return template_id;
	     }

	    public void setPage(String page) {
	         this.page = page;
	     }
	     public String getPage() {
	         return page;
	     }

	    public void setForm_id(String form_id) {
	         this.form_id = form_id;
	     }
	     public String getForm_id() {
	         return form_id;
	     }
	    public void setEmphasis_keyword(String emphasis_keyword) {
	         this.emphasis_keyword = emphasis_keyword;
	     }
	     public String getEmphasis_keyword() {
	         return emphasis_keyword;
	     }

	
}
