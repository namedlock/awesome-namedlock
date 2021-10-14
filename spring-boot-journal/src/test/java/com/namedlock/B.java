package com.namedlock;

import java.util.Set;

public class B {
    private String name;
    private Set<String> sets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSets() {
        return sets;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                '}';
    }
}
