package com.gm.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@SerializedName("userId")
	private String userId = null;

	@SerializedName("firstName")
	private String firstName = null;

	@SerializedName("middleName")
	private String middleName = null;

	@SerializedName("lastName")
	private String lastName = null;

	@SerializedName("title")
	private String title = null;

	@SerializedName("nameSuffix")
	private String nameSuffix = null;

	@SerializedName("phones")
	private List<Phone> phones = null;

	@SerializedName("emailAddress")
	private String emailAddress = null;

	/**
	 * Gets or Sets preferredLanguage
	 */
	@JsonAdapter(PreferredLanguageEnum.Adapter.class)
	public enum PreferredLanguageEnum {
		EN_US("en-US"),

		FR_CA("fr-CA"),

		ZH_CN("zh-CN"),

		EN_CA("en-CA"),

		AR_AE("ar-AE"),

		ES_MX("es-MX");

		private String value;

		PreferredLanguageEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static PreferredLanguageEnum fromValue(String text) {
			for (PreferredLanguageEnum b : PreferredLanguageEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}

		public static class Adapter extends TypeAdapter<PreferredLanguageEnum> {
			@Override
			public void write(final JsonWriter jsonWriter, final PreferredLanguageEnum enumeration) throws IOException {
				jsonWriter.value(enumeration.getValue());
			}

			@Override
			public PreferredLanguageEnum read(final JsonReader jsonReader) throws IOException {
				String value = jsonReader.nextString();
				return PreferredLanguageEnum.fromValue(String.valueOf(value));
			}
		}
	}

	@SerializedName("preferredLanguage")
	private PreferredLanguageEnum preferredLanguage = null;

	@SerializedName("addresses")
	private List<Address> addresses = null;

	@SerializedName("gcin")
	private String gcin = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public PreferredLanguageEnum getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(PreferredLanguageEnum preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getGcin() {
		return gcin;
	}

	public void setGcin(String gcin) {
		this.gcin = gcin;
	}

}
