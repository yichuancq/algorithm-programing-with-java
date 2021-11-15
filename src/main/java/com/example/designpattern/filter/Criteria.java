package com.example.designpattern.filter;

import java.util.List;

public interface Criteria {

    /**
     * @param persons
     * @return
     */
    public List<Person> meetCriteria(List<Person> persons);
}