package com.solace.covid19.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class COVID19UpdatePopulationStats extends COVID19UpdateSchema {

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    private Population population;

    public static class Population
    {
        public Population(Double percent_infected)
        {
            this.percent_infected = percent_infected;
        }
        public Double getPercent_infected() {
            return percent_infected;
        }

        public void setPercent_infected(Double percent_infected) {
            this.percent_infected = percent_infected;
        }

        @JsonProperty("Percent_Infected")
        private Double percent_infected;
    }
    public  COVID19UpdatePopulationStats(COVID19UpdateSchema update)
    {
        this.setAttributes(update.getAttributes());
        this.setGeometry(update.getGeometry());
    }
}
