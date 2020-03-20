
package com.solace.covid19.splitter;


import com.fasterxml.jackson.annotation.JsonProperty;

public  class RawJHUCSSUCOVID19 {

	public RawJHUCSSUCOVID19 () {
	}


	public RawJHUCSSUCOVID19 (Features[] features, String globalIdFieldName, String objectIdFieldName, SpatialReference spatialReference, Fields[] fields, UniqueIdField uniqueIdField, String geometryType) {
		this.features = features;
		this.globalIdFieldName = globalIdFieldName;
		this.objectIdFieldName = objectIdFieldName;
		this.spatialReference = spatialReference;
		this.fields = fields;
		this.uniqueIdField = uniqueIdField;
		this.geometryType = geometryType;
	}


	private Features[] features;
	private String globalIdFieldName;
	private String objectIdFieldName;
	private SpatialReference spatialReference;
	private Fields[] fields;
	private UniqueIdField uniqueIdField;
	private String geometryType;

	public Features[] getFeatures() {
		return features;
	}

	public RawJHUCSSUCOVID19 setFeatures(Features[] features) {
		this.features = features;
		return this;
	}

	public static  class Features {

		public Features () {
		}


		public Features (Attributes attributes, Geometry geometry) {
			this.attributes = attributes;
			this.geometry = geometry;
		}


		private Attributes attributes;
		private Geometry geometry;

		public Attributes getAttributes() {
			return attributes;
		}

		public Features setAttributes(Attributes attributes) {
			this.attributes = attributes;
			return this;
		}

		public static  class Attributes {

			public Attributes () {
			}


			public Attributes (Double long_, Integer recovered, String countryRegion, Integer active, Long lastUpdate, Integer deaths, Integer confirmed, String provinceState, Double lat) {
				this.long_ = long_;
				this.recovered = recovered;
				this.countryRegion = countryRegion;
				this.active = active;
				this.lastUpdate = lastUpdate;
				this.deaths = deaths;
				this.confirmed = confirmed;
				this.provinceState = provinceState;
				this.lat = lat;
			}

			@JsonProperty("Long_")
			private Double long_;
			@JsonProperty("Recovered")
			private Integer recovered;
			@JsonProperty("Country_Region")
			private String countryRegion;
			@JsonProperty("Active")
			private Integer active;
			@JsonProperty("Last_Update")
			private Long lastUpdate;
			@JsonProperty("Deaths")
			private Integer deaths;
			@JsonProperty("Confirmed")
			private Integer confirmed;
			@JsonProperty("Province_State")
			private String provinceState;
			@JsonProperty("Lat")
			private Double lat;


			public Double getLong_() {
				return long_;
			}

			public Attributes setLong_(Double long_) {
				this.long_ = long_;
				return this;
			}

			public Integer getRecovered() {
				return recovered;
			}

			public Attributes setRecovered(Integer recovered) {
				this.recovered = recovered;
				return this;
			}

			public String getCountryRegion() {
				return countryRegion;
			}

			public Attributes setCountryRegion(String countryRegion) {
				this.countryRegion = countryRegion;
				return this;
			}

			public Integer getActive() {
				return active;
			}

			public Attributes setActive(Integer active) {
				this.active = active;
				return this;
			}

			public Long getLastUpdate() {
				return lastUpdate;
			}

			public Attributes setLastUpdate(Long lastUpdate) {
				this.lastUpdate = lastUpdate;
				return this;
			}

			public Integer getDeaths() {
				return deaths;
			}

			public Attributes setDeaths(Integer deaths) {
				this.deaths = deaths;
				return this;
			}

			public Integer getConfirmed() {
				return confirmed;
			}

			public Attributes setConfirmed(Integer confirmed) {
				this.confirmed = confirmed;
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
						+ " long: " + long_
						+ " recovered: " + recovered
						+ " countryRegion: " + countryRegion
						+ " active: " + active
						+ " lastUpdate: " + lastUpdate
						+ " deaths: " + deaths
						+ " confirmed: " + confirmed
						+ " provinceState: " + provinceState
						+ " lat: " + lat
						+ " ]";
			}
		}



		public Geometry getGeometry() {
			return geometry;
		}

		public Features setGeometry(Geometry geometry) {
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
			return "Features ["
					+ " attributes: " + attributes
					+ " geometry: " + geometry
					+ " ]";
		}
	}



	public String getGlobalIdFieldName() {
		return globalIdFieldName;
	}

	public RawJHUCSSUCOVID19 setGlobalIdFieldName(String globalIdFieldName) {
		this.globalIdFieldName = globalIdFieldName;
		return this;
	}

	public String getObjectIdFieldName() {
		return objectIdFieldName;
	}

	public RawJHUCSSUCOVID19 setObjectIdFieldName(String objectIdFieldName) {
		this.objectIdFieldName = objectIdFieldName;
		return this;
	}

	public SpatialReference getSpatialReference() {
		return spatialReference;
	}

	public RawJHUCSSUCOVID19 setSpatialReference(SpatialReference spatialReference) {
		this.spatialReference = spatialReference;
		return this;
	}

	public static  class SpatialReference {

		public SpatialReference () {
		}


		public SpatialReference (Integer latestWkid, Integer wkid) {
			this.latestWkid = latestWkid;
			this.wkid = wkid;
		}


		private Integer latestWkid;
		private Integer wkid;

		public Integer getLatestWkid() {
			return latestWkid;
		}

		public SpatialReference setLatestWkid(Integer latestWkid) {
			this.latestWkid = latestWkid;
			return this;
		}

		public Integer getWkid() {
			return wkid;
		}

		public SpatialReference setWkid(Integer wkid) {
			this.wkid = wkid;
			return this;
		}

		public String toString() {
			return "SpatialReference ["
					+ " latestWkid: " + latestWkid
					+ " wkid: " + wkid
					+ " ]";
		}
	}



	public Fields[] getFields() {
		return fields;
	}

	public RawJHUCSSUCOVID19 setFields(Fields[] fields) {
		this.fields = fields;
		return this;
	}

	public static  class Fields {

		public Fields () {
		}


		public Fields (String sqlType, String defaultValue, String domain, String name, Integer length, String alias, String type) {
			this.sqlType = sqlType;
			this.defaultValue = defaultValue;
			this.domain = domain;
			this.name = name;
			this.length = length;
			this.alias = alias;
			this.type = type;
		}


		private String sqlType;
		private String defaultValue;
		private String domain;
		private String name;
		private Integer length;
		private String alias;
		private String type;

		public String getSqlType() {
			return sqlType;
		}

		public Fields setSqlType(String sqlType) {
			this.sqlType = sqlType;
			return this;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public Fields setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
			return this;
		}

		public String getDomain() {
			return domain;
		}

		public Fields setDomain(String domain) {
			this.domain = domain;
			return this;
		}

		public String getName() {
			return name;
		}

		public Fields setName(String name) {
			this.name = name;
			return this;
		}

		public Integer getLength() {
			return length;
		}

		public Fields setLength(Integer length) {
			this.length = length;
			return this;
		}

		public String getAlias() {
			return alias;
		}

		public Fields setAlias(String alias) {
			this.alias = alias;
			return this;
		}

		public String getType() {
			return type;
		}

		public Fields setType(String type) {
			this.type = type;
			return this;
		}

		public String toString() {
			return "Fields ["
					+ " sqlType: " + sqlType
					+ " defaultValue: " + defaultValue
					+ " domain: " + domain
					+ " name: " + name
					+ " length: " + length
					+ " alias: " + alias
					+ " type: " + type
					+ " ]";
		}
	}



	public UniqueIdField getUniqueIdField() {
		return uniqueIdField;
	}

	public RawJHUCSSUCOVID19 setUniqueIdField(UniqueIdField uniqueIdField) {
		this.uniqueIdField = uniqueIdField;
		return this;
	}

	public static  class UniqueIdField {

		public UniqueIdField () {
		}


		public UniqueIdField (Boolean isSystemMaintained, String name) {
			this.isSystemMaintained = isSystemMaintained;
			this.name = name;
		}


		private Boolean isSystemMaintained;
		private String name;

		public Boolean getIsSystemMaintained() {
			return isSystemMaintained;
		}

		public UniqueIdField setIsSystemMaintained(Boolean isSystemMaintained) {
			this.isSystemMaintained = isSystemMaintained;
			return this;
		}

		public String getName() {
			return name;
		}

		public UniqueIdField setName(String name) {
			this.name = name;
			return this;
		}

		public String toString() {
			return "UniqueIdField ["
					+ " isSystemMaintained: " + isSystemMaintained
					+ " name: " + name
					+ " ]";
		}
	}



	public String getGeometryType() {
		return geometryType;
	}

	public RawJHUCSSUCOVID19 setGeometryType(String geometryType) {
		this.geometryType = geometryType;
		return this;
	}

	public String toString() {
		return "RawJHUCSSUCOVID19 ["
				+ " features: " + features
				+ " globalIdFieldName: " + globalIdFieldName
				+ " objectIdFieldName: " + objectIdFieldName
				+ " spatialReference: " + spatialReference
				+ " fields: " + fields
				+ " uniqueIdField: " + uniqueIdField
				+ " geometryType: " + geometryType
				+ " ]";
	}
}

