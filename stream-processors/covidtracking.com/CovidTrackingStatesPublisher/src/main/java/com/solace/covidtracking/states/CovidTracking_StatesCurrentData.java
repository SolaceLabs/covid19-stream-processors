
package com.solace.covidtracking.states;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CovidTracking_StatesCurrentData extends ArrayList<CovidTracking_StatesCurrentData.CovidTracking_StatesCurrentDataItems> {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CovidTracking_StatesCurrentDataItems {

        public CovidTracking_StatesCurrentDataItems() {
        }


        public CovidTracking_StatesCurrentDataItems(String notes, Integer posNeg, Integer death, Integer pending, Integer hospitalizedCurrently, String lastUpdateEt, Integer negativeScore, Integer onVentilatorCumulative, Integer hospitalized, Integer score, Integer negative, Integer total, Integer commercialScore, Integer hospitalizedCumulative, Integer positiveScore, Integer inIcuCumulative, String state, Integer totalTestResults, Integer inIcuCurrently, String checkTimeEt, String dateChecked, Integer negativeRegularScore, String fips, Integer onVentilatorCurrently, String dateModified, Integer positive, Integer recovered, String grade, String hash, String dataQualityGrade) {
            this.posNeg = posNeg;
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
            this.dataQualityGrade = dataQualityGrade;
        }


        private Integer posNeg;
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
        private String dataQualityGrade;

        public Integer getPosNeg() {
            return posNeg;
        }

        public CovidTracking_StatesCurrentDataItems setPosNeg(Integer posNeg) {
            this.posNeg = posNeg;
            return this;
        }

        public String getNotes() {
            return notes;
        }

        public CovidTracking_StatesCurrentDataItems setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Integer getDeath() {
            return death;
        }

        public CovidTracking_StatesCurrentDataItems setDeath(Integer death) {
            this.death = death;
            return this;
        }

        public Integer getPending() {
            return pending;
        }

        public CovidTracking_StatesCurrentDataItems setPending(Integer pending) {
            this.pending = pending;
            return this;
        }

        public Integer getHospitalizedCurrently() {
            return hospitalizedCurrently;
        }

        public CovidTracking_StatesCurrentDataItems setHospitalizedCurrently(Integer hospitalizedCurrently) {
            this.hospitalizedCurrently = hospitalizedCurrently;
            return this;
        }

        public String getLastUpdateEt() {
            return lastUpdateEt;
        }

        public CovidTracking_StatesCurrentDataItems setLastUpdateEt(String lastUpdateEt) {
            this.lastUpdateEt = lastUpdateEt;
            return this;
        }

        public Integer getNegativeScore() {
            return negativeScore;
        }

        public CovidTracking_StatesCurrentDataItems setNegativeScore(Integer negativeScore) {
            this.negativeScore = negativeScore;
            return this;
        }

        public Integer getOnVentilatorCumulative() {
            return onVentilatorCumulative;
        }

        public CovidTracking_StatesCurrentDataItems setOnVentilatorCumulative(Integer onVentilatorCumulative) {
            this.onVentilatorCumulative = onVentilatorCumulative;
            return this;
        }

        public Integer getHospitalized() {
            return hospitalized;
        }

        public CovidTracking_StatesCurrentDataItems setHospitalized(Integer hospitalized) {
            this.hospitalized = hospitalized;
            return this;
        }

        public Integer getScore() {
            return score;
        }

        public CovidTracking_StatesCurrentDataItems setScore(Integer score) {
            this.score = score;
            return this;
        }

        public Integer getNegative() {
            return negative;
        }

        public CovidTracking_StatesCurrentDataItems setNegative(Integer negative) {
            this.negative = negative;
            return this;
        }

        public Integer getTotal() {
            return total;
        }

        public CovidTracking_StatesCurrentDataItems setTotal(Integer total) {
            this.total = total;
            return this;
        }

        public Integer getCommercialScore() {
            return commercialScore;
        }

        public CovidTracking_StatesCurrentDataItems setCommercialScore(Integer commercialScore) {
            this.commercialScore = commercialScore;
            return this;
        }

        public Integer getHospitalizedCumulative() {
            return hospitalizedCumulative;
        }

        public CovidTracking_StatesCurrentDataItems setHospitalizedCumulative(Integer hospitalizedCumulative) {
            this.hospitalizedCumulative = hospitalizedCumulative;
            return this;
        }

        public Integer getPositiveScore() {
            return positiveScore;
        }

        public CovidTracking_StatesCurrentDataItems setPositiveScore(Integer positiveScore) {
            this.positiveScore = positiveScore;
            return this;
        }

        public Integer getInIcuCumulative() {
            return inIcuCumulative;
        }

        public CovidTracking_StatesCurrentDataItems setInIcuCumulative(Integer inIcuCumulative) {
            this.inIcuCumulative = inIcuCumulative;
            return this;
        }

        public String getState() {
            return state;
        }

        public CovidTracking_StatesCurrentDataItems setState(String state) {
            this.state = state;
            return this;
        }

        public Integer getTotalTestResults() {
            return totalTestResults;
        }

        public CovidTracking_StatesCurrentDataItems setTotalTestResults(Integer totalTestResults) {
            this.totalTestResults = totalTestResults;
            return this;
        }

        public Integer getInIcuCurrently() {
            return inIcuCurrently;
        }

        public CovidTracking_StatesCurrentDataItems setInIcuCurrently(Integer inIcuCurrently) {
            this.inIcuCurrently = inIcuCurrently;
            return this;
        }

        public String getCheckTimeEt() {
            return checkTimeEt;
        }

        public CovidTracking_StatesCurrentDataItems setCheckTimeEt(String checkTimeEt) {
            this.checkTimeEt = checkTimeEt;
            return this;
        }

        public String getDateChecked() {
            return dateChecked;
        }

        public CovidTracking_StatesCurrentDataItems setDateChecked(String dateChecked) {
            this.dateChecked = dateChecked;
            return this;
        }

        public Integer getNegativeRegularScore() {
            return negativeRegularScore;
        }

        public CovidTracking_StatesCurrentDataItems setNegativeRegularScore(Integer negativeRegularScore) {
            this.negativeRegularScore = negativeRegularScore;
            return this;
        }

        public String getFips() {
            return fips;
        }

        public CovidTracking_StatesCurrentDataItems setFips(String fips) {
            this.fips = fips;
            return this;
        }

        public Integer getOnVentilatorCurrently() {
            return onVentilatorCurrently;
        }

        public CovidTracking_StatesCurrentDataItems setOnVentilatorCurrently(Integer onVentilatorCurrently) {
            this.onVentilatorCurrently = onVentilatorCurrently;
            return this;
        }

        public String getDateModified() {
            return dateModified;
        }

        public CovidTracking_StatesCurrentDataItems setDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Integer getPositive() {
            return positive;
        }

        public CovidTracking_StatesCurrentDataItems setPositive(Integer positive) {
            this.positive = positive;
            return this;
        }

        public Integer getRecovered() {
            return recovered;
        }

        public CovidTracking_StatesCurrentDataItems setRecovered(Integer recovered) {
            this.recovered = recovered;
            return this;
        }

        public String getGrade() {
            return grade;
        }

        public CovidTracking_StatesCurrentDataItems setGrade(String grade) {
            this.grade = grade;
            return this;
        }

        public String getHash() {
            return hash;
        }

        public CovidTracking_StatesCurrentDataItems setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public String getDataQualityGrade() {
			return dataQualityGrade;
		}


		public void setDataQualityGrade(String dataQualityGrade) {
			this.dataQualityGrade = dataQualityGrade;
		}


		public String toString() {
            return "CovidTracking_StatesCurrentDataItems ["
                    + "posNeg: " + posNeg
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
                    + " dataQualityGrade: " + dataQualityGrade
                    + " ]";
        }
    }
}


