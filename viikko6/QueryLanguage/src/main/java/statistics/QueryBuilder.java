/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.util.ArrayList;
import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author nikoniem
 */
public class QueryBuilder {

    Matcher matcher;
    private ArrayList<Matcher> matchers;
    private int index;
    private String andOr;
    private ArrayList<Matcher> orMatchers;
    private Matcher[] andMatch;
    private Matcher[] orMatch;
    private Or orr;
    private boolean AND;

    public QueryBuilder() {
        this.matchers = new ArrayList<>();
        this.andOr = "and";
        this.AND = true; 

    }

    public Matcher build() {
        if (this.matchers.isEmpty()) {
            return new All();
        } else {

            Matcher[] m = new Matcher[this.matchers.size()];
            for (int i = 0; i < this.matchers.size(); i++) {
                m[i] = this.matchers.get(i);
            }
            
            System.out.println("AND? " + this.AND);
            if (this.AND) {
                return new Or(m);
            } else {
                return new And(m);
            }
            
            
//            System.out.println("this.andOr: " + this.andOr);
//            if (this.andOr.equals("or")) {
//                return new Or(m);
//            } else {
//                return new And(m);
//            }

            

        }

    }

    public QueryBuilder oneOf(Matcher... m) {
        System.out.println("this.andOr: " + this.andOr);
        this.andOr = "or";
        this.AND = false;
        this.matchers.clear();
        this.matchers.add(new Or(m));
        System.out.println("this.andOr: " + this.andOr);
//        for (Matcher mat : m) {
//            this.matchers.add(new Or(mat));
//        }
        return this;

    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;

    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;

    }

    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;

    }


    public QueryBuilder or(Matcher... m) {
        this.orMatchers.add(new Or(m));
        return this;

    }

}
