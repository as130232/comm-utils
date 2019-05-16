package com.example.commutils.constant;

/**
 * 共用字串
 * @author charles.chen
 * @date 2018年6月20日 下午5:29:10
 */
public enum MSG {
	
/****** 符號 ******/
	/*** ?問號 */
	QUESTION_MARK("?"),
	/*** !驚嘆號 */
	EXCLAMATION_MARK("!"),
	/*** ：冒號 */
	COLON_MARK(":"),

	/*** 該*/
	THE("THE"),
	/*** 已*/
	BEEN("BEEN"),
	/*** 和 */
	AND("AND"),
	/*** 是 */
	IS("IS"),
	/*** 不 ***/
	NOT("NOT"),
	/*** 不要 ***/
	NO("NO"),
	/*** 多筆 ***/
	MULTIPLE("MULTIPLE"),
	
/****** 名詞 ******/
	/*** 廠商 */
	COMPANY("COMPANY"),
	/*** 資訊 */
	INFO("INFO"),
	/*** 未知 */
	UNKNOW("UNKNOW"),
	/*** 未給 */
	UNGIVEN("UNGIVEN"),
	/*** 空值 */
	NULL("NULL"),
	/*** 頁面 */
	PAGE("PAGE"),
	/*** 規則 */
	RULE("RULE"),
	/*** 主鍵 */
	PRIMARY_KEY("PRIMARY_KEY"),
	/*** 任務 */
	TASK("TASK"),
	/*** 個人任務 */
	PERSONAL_TASK("PERSONAL_TASK"),
	
/****** 動詞 ******/
	
	/*** 查詢 */
	SELETE("SELETE"),
	/*** 新增 */
	INSERT("INSERT"),
	/*** 修改 */
	UPDATE("UPDATE"),
	/*** 刪除 */
	DELETE("DELETE"),
	
	/*** 失敗 */
	FAILED("FAILED"),
	/*** 重複 */
	REPEAT("REPEAT"),
	/*** 重複 */
	USE("USE"),
	/*** 存在 */
	EXIST("EXIST");
	
	
	public final String i18n;

	private MSG(String i18n) {
		this.i18n = i18n;
	}

}
