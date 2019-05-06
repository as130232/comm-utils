package com.example.commutils.constant;

import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonCreator;

public class USER {
	
	public enum USE_STATUS {
		/** 電極使用狀態:0:空閒 */
		IDLE(0, "USE_STATUS_IDLE"),
		/** 電極使用狀態:1使用中 */
		USED(1, "USE_STATUS_USED"),;
		public final Integer value;
		public final Integer intValue;
		public final String i18n;

		private USE_STATUS(Integer value, String i18n) {
			this.value = value;
			this.i18n = i18n;
			this.intValue = value;
		}
		
		@JsonCreator
		public static USE_STATUS find(Integer intValue) {
			for (USE_STATUS e : USE_STATUS.values()) {
				if (e.intValue == intValue) {
					return e;
				}
			}
			throw new NoSuchElementException(String.format("%s is illegal value.", intValue));
		}
	}
	
	public enum LIFE_STATUS {
		/** 電極生命週期:0:未製造完成 */
		NOT_FINISH(0, "LIFE_STATUS_NOT_FINISH"),
		/** 電極生命週期:1:製造完成 */
		FINISH(1, "LIFE_STATUS_FINISH"),
		/** 電極生命週期:2:保留反修 */
		RETAINED_REPAIR(2, "LIFE_STATUS_RETAINED_REPAIR"),
		/** 電極生命週期:3:報廢 */
		SCRAPPED(3, "LIFE_STATUS_SCRAPPED");

		public final Integer value;
		public final Integer intValue;
		public final String i18n;

		private LIFE_STATUS(Integer value, String i18n) {
			this.value = value;
			this.i18n = i18n;
			this.intValue = value;
		}

		@JsonCreator
		public static LIFE_STATUS find(Integer intValue) {
			for (LIFE_STATUS e : LIFE_STATUS.values()) {
				if (e.intValue == intValue) {
					return e;
				}
			}
			throw new NoSuchElementException(String.format("%s is illegal value.", intValue));
		}
	}
}
