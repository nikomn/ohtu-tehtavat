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
        this.orMatchers = new ArrayList<>();

    }

    public Matcher build() {
        if (this.matchers.isEmpty()) {
            return new All();
        } else {

            Matcher[] m = new Matcher[this.matchers.size()];
            for (int i = 0; i < this.matchers.size(); i++) {
                m[i] = this.matchers.get(i);
                //System.out.println("this.matchers.get(" + i + "): " + this.matchers.get(i));
            }

            this.matchers.clear();
            
            //System.out.println("AND? " + this.AND);
            if (this.AND) {
                Matcher matchAnd = new And(m);
                //System.out.println("And");
                return matchAnd;
            } else {
                //System.out.println("Or");
                //System.out.println("Tulostuu, mutta ei tulostu?");
                Matcher matchOr = new Or(m);
                return matchOr;
            }


        }

    }

    public QueryBuilder oneOf(Matcher... m) {
        //System.out.println("One of");

        this.AND = false;
        //System.out.println(this.AND);

        this.matchers.clear();
        for (int i = 0; i < m.length; i++) {
            this.matchers.add(m[i]);
        }

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
