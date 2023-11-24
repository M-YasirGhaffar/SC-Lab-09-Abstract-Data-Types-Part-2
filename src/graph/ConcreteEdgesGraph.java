/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Represents Graph made of edges connecting vertices
    // Representation invariant:
    //   Contains a set of edges connected through vertices
    // Safety from rep exposure:
    //   All fields are private
    //	 Edges and vertices consist of only strings that are immutable
    
    // TODO constructor
    public ConcreteEdgesGraph() {
    }
    
    // TODO checkRep
    public void checkRep() {
    	for (Edge edge: edges) {
    		assert(vertices.contains(edge.getSource()));
    		assert(vertices.contains(edge.getTarget()));
    		
    	}
    }
    
    @Override public boolean add(String vertex) {
        if (vertices.contains(vertex)) {
        	return false;
        }
        else {
        	vertices.add(vertex);
        	return true;
        }
    }
    
    @Override public int set(String source, String target, int weight) {
        add(source);
        add(target);
        int ret = 0;
        
        boolean exists=false;
        for (Edge edge: edges) {
        	if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
        		exists= true;
        		ret = edge.getWeight();
        		if (weight == 0) {
        			edges.remove(edge);
        		}
        		else {
        			edge.setWeight(weight);
        		}
        	}
        }
        
        if (!exists) {
        	edges.add(new Edge(source, target, weight));
        }
        
        
        return ret;
        
        
    }
    
    @Override public boolean remove(String vertex) {
    	if (vertices.contains(vertex)) {
    		vertices.remove(vertex);
            for (Edge edge: edges) {
            	if (edge.getSource()==vertex || edge.getTarget()==vertex) {
            		edges.remove(edge);
            	}
            }
            return true;
    	}
    	return false;
    }
    
    @Override public Set<String> vertices() {
        return vertices;
    }
    
    @Override public Map<String, Integer> sources(String target) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for (Edge edge: edges) {
        	if (edge.getTarget().equals(target)) {
        		map.put(edge.getSource(), edge.getWeight());
        	}
        }
    	return map;
    }
    
    @Override public Map<String, Integer> targets(String source) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for (Edge edge: edges) {
        	if (edge.getSource().equals(source)) {
        		map.put(edge.getTarget(), edge.getWeight());
        	}
        }
    	return map;
    }
    
    // TODO toString()
    public String toString() {
    	String ret="";
    	for (Edge edge: edges) {
    		ret+= edge.toString();
    	}
    	return ret;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    
    // TODO fields
    private final String vertex1;
    private final String vertex2;
    private int weight;
    
    // Abstraction function:
    //   Represents an edge between vertex1 and vertex2
    // Representation invariant:
    //   vertex1 and vertex2 are non empty Strings
    // Safety from rep exposure:
    //   Strings are immutable and set to final so edge is immutable
    
    // TODO constructor
    public Edge(String a, String b, int w) {
    	vertex1 = a;
    	vertex2= b;
    	weight = w;
    }
    
    // TODO checkRep
    private void checkRep() {
    	assert (!vertex1.isEmpty());
    	assert (!vertex2.isEmpty());
    }
    
    // TODO methods
    public String getSource() {
    	return vertex1;
    }
    
    public String getTarget() {
    	return vertex2;
    }
    
    public int getWeight() {
    	return weight;
    }
    
    public void setWeight(int w) {
    	weight = w;
    }
    
    // TODO toString()
    public String toString() {
    	return "("+ vertex1 + "," + vertex2+ "," + weight + ")";
    }
    
}