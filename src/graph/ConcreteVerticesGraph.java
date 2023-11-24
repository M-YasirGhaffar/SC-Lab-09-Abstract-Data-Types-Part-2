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
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
 // Abstraction function:
    //   Represents Graph made vertices
    // Representation invariant:
    //   Contains a set of vertices
    // Safety from rep exposure:
    //   All fields are private
    //	 vertices consist of only strings that are immutable
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
    	for (Vertex vert: vertices) {
    		String lab = vert.getlabel();
    		if (lab.equals(vertex)) {
    			
    			return false;
    		}
    	}
        vertices.add(new Vertex(vertex));
        return true;
    }
    
    @Override public int set(String source, String target, int weight) {
    	add(source);
    	add(target);
    	
    	int ret=0;
    	for (Vertex vertex: vertices) {
    		if (vertex.getlabel().equals(source)) {
    			ret = vertex.addTarget(target, weight);
    		}
    	}
    	return ret;
    }
    
    @Override public boolean remove(String vertex) {
    	boolean removed=false;
    	for (Vertex vert: vertices) {
    		String lab = vert.getlabel();
    		if (lab.equals(vertex)) {
    			vertices.remove(vert);
    			return true;
    		}
    		removed = vert.removeTarget(vertex);
    	}
    	return removed;
    }
    
    @Override public Set<String> vertices() {
    	Set<String> verts= new HashSet<String>();
    	for (Vertex vert: vertices) {
    		verts.add(vert.getlabel());
    	}
        return verts;
    }
    
    @Override public Map<String, Integer> sources(String target) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for (Vertex vert: vertices) {
    		Map<String, Integer> targets = new HashMap<String, Integer>();
    		targets = vert.getTargets();
    		if(targets.containsKey(target)) {
    			map.put(vert.getlabel(),targets.get(target));
    		}
    	}
    	return map;
    }
    
    @Override public Map<String, Integer> targets(String source) {
    	
    	for (Vertex vert: vertices) {
    		String lab = vert.getlabel();
    		if (lab.equals(source)) {
    			return vert.getTargets();
    		}
    
    	}
    	return new HashMap<String, Integer>();
    }
    
    // TODO toString()
    public String toString() {
    	String ret="";
    	for (Vertex vertex: vertices) {
    		String vertex1 = vertex.getlabel();
    		Map<String, Integer> map = new HashMap<String, Integer>();
    		map = vertex.getTargets();
    		for (String vertex2: map.keySet()) {
    			ret+= "("+ vertex1 + "," + vertex2+ "," + map.get(vertex2) + ")";
    		}
    		
    	}
    	
    	return ret;
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // TODO fields
    private final String label;
    Map<String, Integer> targets = new HashMap<String, Integer>();
    // Abstraction function:
    //   represents a vertex and all its targets in graph
    // Representation invariant:
    //   non empty label
    // Safety from rep exposure:
    //   String is immutable and final
    
    // TODO constructor
    public Vertex(String l) {
    	label=l;
    }
    
    // TODO checkRep
    private void checkRep() {
    	assert (!label.isEmpty());
    }
    
    // TODO methods
    public int addTarget(String l, int w) {
    	int ret=0;
    	if (targets.keySet().contains(l)) {
    		ret = targets.get(l);
    		targets.replace(l, w);
    	}
    	else {
    		targets.put(l,w);
    	}
    	return ret;
    }
    
    public Map<String, Integer> getTargets(){
    	return targets;
    }
    
    public String getlabel() {
    	return label;
    }
    
    public boolean removeTarget(String l) {
    	if (targets.keySet().contains(l)) {
    		targets.remove(l);
    		return true;
    	}
    	return false;
    }
    
    
    
    // TODO toString()
    public String toString() {
    	return label + " points to " + targets.toString();
    }
}