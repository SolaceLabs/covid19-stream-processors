
package com.solace.covidtracking.states;


public  class CovidTracking_StatesCurrentData {

	public CovidTracking_StatesCurrentData () {
	}


	public CovidTracking_StatesCurrentData (TheRootSchema[] theRootSchema) {
		this.theRootSchema = theRootSchema;
	}

	private TheRootSchema[] theRootSchema;


	public static  class TheRootSchema {

		public TheRootSchema () {
		}


		public TheRootSchema (String notes, Integer death, Integer pending, Integer hospitalizedCurrently, String lastUpdateEt, Integer negativeScore, Integer onVentilatorCumulative, Integer hospitalized, Integer score, Integer negative, Integer total, Integer commercialScore, Integer hospitalizedCumulative, Integer positiveScore, Integer inIcuCumulative, String state, Integer totalTestResults, Integer inIcuCurrently, String checkTimeEt, String dateChecked, Integer negativeRegularScore, String fips, Integer onVentilatorCurrently, String dateModified, Integer positive, Integer recovered, String grade, String hash) {
			this.notes = notes;
			this.death = death;
			this.pending = pending;
			this.hospitalizedCurrently = hospitalizedCurrently;
			this.lastUpdateEt = lastUpdateEt;
			this.negativeScore = negativeScore;
			this.onVentilatorCumulative = onVentilatorCumulative;
			this.hospitalized = hospitalized;
			this.score = score;
			this.negative = negative;
			this.total = total;
			this.commercialScore = commercialScore;
			this.hospitalizedCumulative = hospitalizedCumulative;
			this.positiveScore = positiveScore;
			this.inIcuCumulative = inIcuCumulative;
			this.state = state;
			this.totalTestResults = totalTestResults;
			this.inIcuCurrently = inIcuCurrently;
			this.checkTimeEt = checkTimeEt;
			this.dateChecked = dateChecked;
			this.negativeRegularScore = negativeRegularScore;
			this.fips = fips;
			this.onVentilatorCurrently = onVentilatorCurrently;
			this.dateModified = dateModified;
			this.positive = positive;
			this.recovered = recovered;
			this.grade = grade;
			this.hash = hash;
		}

		private String notes;
		private Integer death;
		private Integer pending;
		private Integer hospitalizedCurrently;
		private String lastUpdateEt;
		private Integer negativeScore;
		private Integer onVentilatorCumulative;
		private Integer hospitalized;
		private Integer score;
		private Integer negative;
		private Integer total;
		private Integer commercialScore;
		private Integer hospitalizedCumulative;
		private Integer positiveScore;
		private Integer inIcuCumulative;
		private String state;
		private Integer totalTestResults;
		private Integer inIcuCurrently;
		private String checkTimeEt;
		private String dateChecked;
		private Integer negativeRegularScore;
		private String fips;
		private Integer onVentilatorCurrently;
		private String dateModified;
		private Integer positive;
		private Integer recovered;
		private String grade;
		private String hash;

		public String getNotes() {
			return notes;
		}

		public TheRootSchema setNotes(String notes) {
			this.notes = notes;
			return this;
		}

		public Integer getDeath() {
			return death;
		}

		public TheRootSchema setDeath(Integer death) {
			this.death = death;
			return this;
		}

		public Integer getPending() {
			return pending;
		}

		public TheRootSchema setPending(Integer pending) {
			this.pending = pending;
			return this;
		}

		public Integer getHospitalizedCurrently() {
			return hospitalizedCurrently;
		}

		public TheRootSchema setHospitalizedCurrently(Integer hospitalizedCurrently) {
			this.hospitalizedCurrently = hospitalizedCurrently;
			return this;
		}

		public String getLastUpdateEt() {
			return lastUpdateEt;
		}

		public TheRootSchema setLastUpdateEt(String lastUpdateEt) {
			this.lastUpdateEt = lastUpdateEt;
			return this;
		}

		public Integer getNegativeScore() {
			return negativeScore;
		}

		public TheRootSchema setNegativeScore(Integer negativeScore) {
			this.negativeScore = negativeScore;
			return this;
		}

		public Integer getOnVentilatorCumulative() {
			return onVentilatorCumulative;
		}

		public TheRootSchema setOnVentilatorCumulative(Integer onVentilatorCumulative) {
			this.onVentilatorCumulative = onVentilatorCumulative;
			return this;
		}

		public Integer getHospitalized() {
			return hospitalized;
		}

		public TheRootSchema setHospitalized(Integer hospitalized) {
			this.hospitalized = hospitalized;
			return this;
		}

		public Integer getScore() {
			return score;
		}

		public TheRootSchema setScore(Integer score) {
			this.score = score;
			return this;
		}

		public Integer getNegative() {
			return negative;
		}

		public TheRootSchema setNegative(Integer negative) {
			this.negative = negative;
			return this;
		}

		public Integer getTotal() {
			return total;
		}

		public TheRootSchema setTotal(Integer total) {
			this.total = total;
			return this;
		}

		public Integer getCommercialScore() {
			return commercialScore;
		}

		public TheRootSchema setCommercialScore(Integer commercialScore) {
			this.commercialScore = commercialScore;
			return this;
		}

		public Integer getHospitalizedCumulative() {
			return hospitalizedCumulative;
		}

		public TheRootSchema setHospitalizedCumulative(Integer hospitalizedCumulative) {
			this.hospitalizedCumulative = hospitalizedCumulative;
			return this;
		}

		public Integer getPositiveScore() {
			return positiveScore;
		}

		public TheRootSchema setPositiveScore(Integer positiveScore) {
			this.positiveScore = positiveScore;
			return this;
		}

		public Integer getInIcuCumulative() {
			return inIcuCumulative;
		}

		public TheRootSchema setInIcuCumulative(Integer inIcuCumulative) {
			this.inIcuCumulative = inIcuCumulative;
			return this;
		}

		public String getState() {
			return state;
		}

		public TheRootSchema setState(String state) {
			this.state = state;
			return this;
		}

		public Integer getTotalTestResults() {
			return totalTestResults;
		}

		public TheRootSchema setTotalTestResults(Integer totalTestResults) {
			this.totalTestResults = totalTestResults;
			return this;
		}

		public Integer getInIcuCurrently() {
			return inIcuCurrently;
		}

		public TheRootSchema setInIcuCurrently(Integer inIcuCurrently) {
			this.inIcuCurrently = inIcuCurrently;
			return this;
		}

		public String getCheckTimeEt() {
			return checkTimeEt;
		}

		public TheRootSchema setCheckTimeEt(String checkTimeEt) {
			this.checkTimeEt = checkTimeEt;
			return this;
		}

		public String getDateChecked() {
			return dateChecked;
		}

		public TheRootSchema setDateChecked(String dateChecked) {
			this.dateChecked = dateChecked;
			return this;
		}

		public Integer getNegativeRegularScore() {
			return negativeRegularScore;
		}

		public TheRootSchema setNegativeRegularScore(Integer negativeRegularScore) {
			this.negativeRegularScore = negativeRegularScore;
			return this;
		}

		public String getFips() {
			return fips;
		}

		public TheRootSchema setFips(String fips) {
			this.fips = fips;
			return this;
		}

		public Integer getOnVentilatorCurrently() {
			return onVentilatorCurrently;
		}

		public TheRootSchema setOnVentilatorCurrently(Integer onVentilatorCurrently) {
			this.onVentilatorCurrently = onVentilatorCurrently;
			return this;
		}

		public String getDateModified() {
			return dateModified;
		}

		public TheRootSchema setDateModified(String dateModified) {
			this.dateModified = dateModified;
			return this;
		}

		public Integer getPositive() {
			return positive;
		}

		public TheRootSchema setPositive(Integer positive) {
			this.positive = positive;
			return this;
		}

		public Integer getRecovered() {
			return recovered;
		}

		public TheRootSchema setRecovered(Integer recovered) {
			this.recovered = recovered;
			return this;
		}

		public String getGrade() {
			return grade;
		}

		public TheRootSchema setGrade(String grade) {
			this.grade = grade;
			return this;
		}

		public String getHash() {
			return hash;
		}

		public TheRootSchema setHash(String hash) {
			this.hash = hash;
			return this;
		}

		public String toString() {
			return "TheRootSchema ["
			+ " notes: " + notes
			+ " death: " + death
			+ " pending: " + pending
			+ " hospitalizedCurrently: " + hospitalizedCurrently
			+ " lastUpdateEt: " + lastUpdateEt
			+ " negativeScore: " + negativeScore
			+ " onVentilatorCumulative: " + onVentilatorCumulative
			+ " hospitalized: " + hospitalized
			+ " score: " + score
			+ " negative: " + negative
			+ " total: " + total
			+ " commercialScore: " + commercialScore
			+ " hospitalizedCumulative: " + hospitalizedCumulative
			+ " positiveScore: " + positiveScore
			+ " inIcuCumulative: " + inIcuCumulative
			+ " state: " + state
			+ " totalTestResults: " + totalTestResults
			+ " inIcuCurrently: " + inIcuCurrently
			+ " checkTimeEt: " + checkTimeEt
			+ " dateChecked: " + dateChecked
			+ " negativeRegularScore: " + negativeRegularScore
			+ " fips: " + fips
			+ " onVentilatorCurrently: " + onVentilatorCurrently
			+ " dateModified: " + dateModified
			+ " positive: " + positive
			+ " recovered: " + recovered
			+ " grade: " + grade
			+ " hash: " + hash
			+ " ]";
		}
	}




	public String toString() {
		return "CovidTracking_StatesCurrentData ["
		+ " ]";
	}
}

