
package com.solace.covid19.splitter.regional;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public  class COVID19UpdateSchema {

	public COVID19UpdateSchema () {
	}


	public COVID19UpdateSchema (Attributes attributes, Geometry geometry) {
		this.attributes = attributes;
		this.geometry = geometry;
	}


	private Attributes attributes;
	private Geometry geometry;

	public Attributes getAttributes() {
		return attributes;
	}

	public COVID19UpdateSchema setAttributes(Attributes attributes) {
		this.attributes = attributes;
		return this;
	}

	public static  class Attributes {

		public Attributes () {
		}


		public Attributes (Integer recovered, Double long_, String countryRegion, Long lastUpdate, Integer active, Integer confirmed, Integer deaths, String provinceState, Double lat) {
			this.recovered = recovered;
			this.long_ = long_;
			this.countryRegion = countryRegion;
			this.lastUpdate = lastUpdate;
			this.active = active;
			this.confirmed = confirmed;
			this.deaths = deaths;
			this.provinceState = provinceState;
			this.lat = lat;
		}


		@JsonProperty("Long_")
		private Double long_;
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		@JsonProperty("Recovered")
		private Integer recovered;
		@JsonProperty("Country_Region")
		private String countryRegion;
		@JsonProperty("Active")
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		private Integer active;
		@JsonProperty("Last_Update")
		private Long lastUpdate;
		@JsonProperty("Deaths")
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		private Integer deaths;
		@JsonProperty("Confirmed")
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		private Integer confirmed;
		@JsonProperty("Province_State")
		private String provinceState;
		@JsonProperty("Lat")
		private Double lat;

		public Integer getRecovered() {
			return recovered;
		}

		public Attributes setRecovered(Integer recovered) {
			this.recovered = recovered;
			return this;
		}

		public Double getLong_() {
			return long_;
		}

		public Attributes setLong_(Double long_) {
			this.long_ = long_;
			return this;
		}

		public String getCountryRegion() {
			return countryRegion;
		}

		public Attributes setCountryRegion(String countryRegion) {
			this.countryRegion = countryRegion;
			return this;
		}

		public Long getLastUpdate() {
			return lastUpdate;
		}

		public Attributes setLastUpdate(Long lastUpdate) {
			this.lastUpdate = lastUpdate;
			return this;
		}

		public Integer getActive() {
			return active;
		}

		public Attributes setActive(Integer active) {
			this.active = active;
			return this;
		}

		public Integer getConfirmed() {
			return confirmed;
		}

		public Attributes setConfirmed(Integer confirmed) {
			this.confirmed = confirmed;
			return this;
		}

		public Integer getDeaths() {
			return deaths;
		}

		public Attributes setDeaths(Integer deaths) {
			this.deaths = deaths;
			return this;
		}

		public String getProvinceState() {
			return provinceState;
		}

		public Attributes setProvinceState(String provinceState) {
			this.provinceState = provinceState;
			return this;
		}

		public Double getLat() {
			return lat;
		}

		public Attributes setLat(Double lat) {
			this.lat = lat;
			return this;
		}

		public String toString() {
			return "Attributes ["
			+ " recovered: " + recovered
			+ " long: " + long_
			+ " countryRegion: " + countryRegion
			+ " lastUpdate: " + lastUpdate
			+ " active: " + active
			+ " confirmed: " + confirmed
			+ " deaths: " + deaths
			+ " provinceState: " + provinceState
			+ " lat: " + lat
			+ " ]";
		}
	}



	public Geometry getGeometry() {
		return geometry;
	}

	public COVID19UpdateSchema setGeometry(Geometry geometry) {
		this.geometry = geometry;
		return this;
	}

	public static  class Geometry {

		public Geometry () {
		}


		public Geometry (Double x, Double y) {
			this.x = x;
			this.y = y;
		}


		private Double x;
		private Double y;

		public Double getX() {
			return x;
		}

		public Geometry setX(Double x) {
			this.x = x;
			return this;
		}

		public Double getY() {
			return y;
		}

		public Geometry setY(Double y) {
			this.y = y;
			return this;
		}

		public String toString() {
			return "Geometry ["
			+ " x: " + x
			+ " y: " + y
			+ " ]";
		}
	}



	public String toString() {
		return "COVID19UpdateSchema ["
		+ " attributes: " + attributes
		+ " geometry: " + geometry
		+ " ]";
	}
}

