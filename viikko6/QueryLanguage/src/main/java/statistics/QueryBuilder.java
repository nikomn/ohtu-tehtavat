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

    public QueryBuilder() {
        //matcher = new And(matchers);
        this.index = 0;
        this.matchers = new ArrayList<>();
    }

    public Matcher build() {
        if (this.matchers.isEmpty()) {
            return new All();
        } else {
            Matcher[] m = new Matcher[this.index];
            for (int i = 0; i < this.index; i++) {
                m[i] = this.matchers.get(i);
                
            }
            return new And(m);
        }
        

    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        this.index++;
        return this;

    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        this.index++;
        return this;

    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        this.index++;
        return this;
        
    }
    
    public QueryBuilder and(Matcher... matchers) {
        this.matcher = new And(matchers);
        return this;

    }
    
    public QueryBuilder not(Matcher... matchers) {
        this.matcher = new Not(matchers);
        return this;
        
    }
    
    public QueryBuilder or(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
        
    }

    

}
