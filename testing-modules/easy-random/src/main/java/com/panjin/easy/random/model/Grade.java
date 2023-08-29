package com.panjin.easy.random.model;

import java.util.StringJoiner;

/**
 * @author panjin
 */
public class Grade {

    private final int grade;

    public Grade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Grade.class.getSimpleName() + "[", "]").add("grade=" + grade)
            .toString();
    }
}
