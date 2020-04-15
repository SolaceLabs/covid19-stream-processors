package com.solace.covid19.data.models;

public class COVID19Utilities {
	public static COVID19UpdateSchema cloneFeature(RawJHUCSSUCOVID19.Features feature) {
		COVID19UpdateSchema result = new COVID19UpdateSchema();
		COVID19UpdateSchema.Attributes attributes = new COVID19UpdateSchema.Attributes();
		COVID19UpdateSchema.Geometry geometry = new COVID19UpdateSchema.Geometry();

		if (feature.getAttributes() != null) {
			result.setAttributes(attributes);
			attributes.setActive(feature.getAttributes().getActive());
			attributes.setConfirmed(feature.getAttributes().getConfirmed());
			attributes.setCountryRegion(feature.getAttributes().getCountryRegion());
			attributes.setDeaths(feature.getAttributes().getDeaths());
			attributes.setLastUpdate(feature.getAttributes().getLastUpdate());
			attributes.setLat(feature.getAttributes().getLat());
			attributes.setLong_(feature.getAttributes().getLong_());
			attributes.setProvinceState(feature.getAttributes().getProvinceState());
			attributes.setRecovered(feature.getAttributes().getRecovered());
		} else {
			result.setAttributes(null);
		}

		if (feature.getGeometry() != null) {
			result.setGeometry(geometry);
			geometry.setX(feature.getGeometry().getX());
			geometry.setY(feature.getGeometry().getY());
		} else {
			result.setGeometry(null);
		}

		return result;
	}

	public static enum DataAttribute {
		DEATHS, ACTIVE, RECOVERED, CONFIRMED
	}

	public static COVID19UpdateSchema cloneOnlyAttribute(RawJHUCSSUCOVID19.Features feature, DataAttribute attr) {
		COVID19UpdateSchema result = new COVID19UpdateSchema();
		COVID19UpdateSchema.Attributes attributes = new COVID19UpdateSchema.Attributes();
		COVID19UpdateSchema.Geometry geometry = new COVID19UpdateSchema.Geometry();
		if (feature.getAttributes() != null) {
			result.setAttributes(attributes);

			switch (attr) {
			case ACTIVE:
				attributes.setActive(feature.getAttributes().getActive());
				break;
			case RECOVERED:
				attributes.setRecovered(feature.getAttributes().getRecovered());
				break;
			case CONFIRMED:
				attributes.setConfirmed(feature.getAttributes().getConfirmed());
				break;
			case DEATHS:
				attributes.setDeaths(feature.getAttributes().getDeaths());
				break;
			}

			attributes.setCountryRegion(feature.getAttributes().getCountryRegion());
			attributes.setLastUpdate(feature.getAttributes().getLastUpdate());
			attributes.setLat(feature.getAttributes().getLat());
			attributes.setLong_(feature.getAttributes().getLong_());
			attributes.setProvinceState(feature.getAttributes().getProvinceState());
		} else {
			result.setAttributes(null);
		}

		if (feature.getGeometry() != null) {
			result.setGeometry(geometry);
			geometry.setX(feature.getGeometry().getX());
			geometry.setY(feature.getGeometry().getY());
		} else {
			result.setGeometry(null);
		}
		
		return result;
	}
}
