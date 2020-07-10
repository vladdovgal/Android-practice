package com.dovhal.p0361sqlitequery;

import java.util.concurrent.atomic.AtomicInteger;

public class Country {
    private int id;
    private String name;
    private int population;
    private String continent;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Country(String name, int population, String region) {
        this.id = atomicInteger.incrementAndGet();
        this.name = name;
        this.population = population;
        this.continent = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public String getRegion() {
        return continent;
    }

    public void setRegion(String region) {
        this.continent = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", continent='" + continent + '\'' +
                '}';
    }
}
