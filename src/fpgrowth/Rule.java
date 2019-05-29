package fpgrowth;

import java.util.HashMap;

public class Rule {
	
	private HashMap<String, String> values = new HashMap<String, String>();
	private int counter;
	
	public Rule(String line) {
		String[] arr = line.split(" ");
    	this.counter = Integer.valueOf(arr[arr.length-1]);
    	
    	for (int i = 0; i < arr.length-1; i++) {
    		String[] atribut = arr[i].split("=");
    		
    		String key = atribut[0].replace("_", " ");
    		String value = atribut[1].replace("_", " ");
    		this.values.put(key, value);
    	}
	}

	public HashMap<String, String> getValues() {
		return values;
	}

	public int getCounter() {
		return counter;
	}
	
	private String valuesToStr() {
		String ret = "";
		for (String key : this.values.keySet()) {
			ret += (key + "->" + values.get(key) + "\t");
		}
		
		
		return ret;
	}

	@Override
	public String toString() {
		return valuesToStr() + "\t" + this.counter;
	}
	
	

}
