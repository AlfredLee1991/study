package com.framework.common.req;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 通用请求对象<br>
 *
 * #date 2015-10-07<br>
 * #since 1.0.0<br>
 */
public class ReqParam<T> {

	/**
	 * 客户端请求的随机数<br>
	 * etc: 36<br>
	 * 必填<br>
	 */
	private int ts = 0;

	/**
	 * 账号 - 手机app的登陆账号手机号/<br>
	 * etc: jason<br>
	 * 必填<br>
	 */
	private String account = "";

	/**
	 * 账号类型<br>
	 * etc:1.医生/2.病人
	 */
	private Integer accountType;

	/**
	 * 不同的app类型<br>
	 */
	private String appCode = "";

	/**
	 * 设备ID - android的IMEI/ios的UUID<br>
	 * etc:afef3bb197a6e5d1 <br>
	 * 必填
	 */
	private String imeiuuid = "";

	/**
	 * 验证摘要 - 验证请求的有效性 - 加密规则参考接口文档说明<br>
	 * etc:e0ac08f1cbb871400f24f3a25fdcd9fb72745520<br>
	 * 必填<br>
	 */
	private String digest = "";

	/**
	 * 来源类型<br>
	 * etc:"android\ios\pc"<br>
	 * 必填<br>
	 */
	private String sourceType = "";
	
	/**
	 * 请求内容 - 方法请求的主题参数<br>
	 * etc:"{\"cmd\":\"getUserList\"}"<br>
	 * 必填<br>
	 */
	private T jsonData = null;


	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public int getTs() {
		return ts;
	}

	public void setTs(int ts) {
		this.ts = ts;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getImeiuuid() {
		return imeiuuid;
	}

	public void setImeiuuid(String imeiuuid) {
		this.imeiuuid = imeiuuid;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public T getJsonData() {
		return jsonData;
	}

	public void setJsonData(T jsonData) {
		this.jsonData = jsonData;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
