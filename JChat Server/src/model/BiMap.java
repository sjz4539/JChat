package model;

import java.util.HashMap;
import java.util.Set;

/**
 * Bidirectional Map. Assigning a pair of objects creates a two-way
 * mapping, replacing any previous mapping that involved either object.
 * 
 * @author Steven Zuchowski
 */
public class BiMap<A, B>{

	private HashMap<A, B> aToB;
	private HashMap<B, A> bToA;
	
	public BiMap(){
		aToB = new HashMap<A, B>();
		bToA = new HashMap<B, A>();
	}
	
	public B putA(A a, B b){
		bToA.put(b, a);
		return aToB.put(a, b);
	}
	
	public A putB(B b, A a){
		aToB.put(a, b);
		return bToA.put(b, a);
	}

	public B getA(A a){
		return aToB.get(a);
	}
	
	public A getB(B b){
		return bToA.get(b);
	}
	
	public B removeA(A a){
		if(aToB.containsKey(a)){
			bToA.remove(getA(a));
		}
		return aToB.remove(a);
	}
	
	public A removeB(B b){
		if(bToA.containsKey(b)){
			aToB.remove(getB(b));
		}
		return bToA.remove(b);
	}
	
	public boolean containsA(A a){
		return aToB.containsKey(a);
	}
	
	public boolean containsB(B b){
		return bToA.containsKey(b);
	}
	
	public void clear(){
		aToB.clear();
		bToA.clear();
	}
	
	public int size(){
		return aToB.size();
	}
	
	public boolean isEmpty(){
		return aToB.isEmpty();
	}
	
	public Set<A> keySetA(){
		return aToB.keySet();
	}
	
	public Set<B> keySetB(){
		return bToA.keySet();
	}
	
}
