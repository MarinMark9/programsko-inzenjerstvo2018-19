/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fpgrowth;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class FPGrowth {

    int threshold, column_count;
    String querry;
    String[] header;
    //fp-tree constructing fileds
    Vector<FPtree> headerTable;
    FPtree fptree;
    //fp-growth
    Map<String, Integer> frequentPatterns;

    public FPGrowth(String querry, int threshold, String[] header) {
        this.threshold = threshold;
        this.querry = querry;
        this.column_count = 3;
        this.header = header;
        
        fptree();
        fpgrowth(fptree, threshold, headerTable);
        //print_solution();
    }

    private FPtree conditional_fptree_constructor(Map<String, Integer> conditionalPatternBase, Map<String, Integer> conditionalItemsMaptoFrequencies, int threshold, Vector<FPtree> conditional_headerTable) {
        //FPTree constructing
        //the null node!
        FPtree conditional_fptree = new FPtree("null");
        conditional_fptree.item = null;
        conditional_fptree.root = true;
        //remember our transactions here has oredering and non-frequent items for condition items
        for (String pattern : conditionalPatternBase.keySet()) {
            //adding to tree
            //removing non-frequents and making a vector instead of string
            Vector<String> pattern_vector = new Vector<String>();
            StringTokenizer tokenizer = new StringTokenizer(pattern);
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken();
                if (conditionalItemsMaptoFrequencies.get(item) >= threshold) {
                    pattern_vector.addElement(item);
                }
            }
            //the insert method
            insert(pattern_vector, conditionalPatternBase.get(pattern), conditional_fptree, conditional_headerTable);
            //end of insert method
        }
        return conditional_fptree;
    }

    private void fptree() {
        //preprocessing fields
        Map<String, Integer> itemsMaptoFrequencies = new HashMap<String, Integer>();
        List<String> sortedItemsbyFrequencies = new LinkedList<String>();
        Vector<String> itemstoRemove = new Vector<String>();
        preProcessing(itemsMaptoFrequencies, sortedItemsbyFrequencies, itemstoRemove);
        construct_fpTree(itemsMaptoFrequencies, sortedItemsbyFrequencies, itemstoRemove);

    }

    private void preProcessing(Map<String, Integer> itemsMaptoFrequencies, List<String> sortedItemsbyFrequencies, Vector<String> itemstoRemove) {
    	//mapping frequents
		ResultSet result = DatabaseData.getDatabaseData(this.querry);
		try {
			while(result.next()) {
				for (int i = 0; i < this.column_count; i++) {
					String temp = result.getString(i+1).replace(" ", "_").replace(",", "");
					
					//System.out.println(this.header[i].replace(" ", "_") + "=" + temp);
					temp = this.header[i].replace(" ", "_") + "=" + temp;
					if (itemsMaptoFrequencies.containsKey(temp)) {
						int count = itemsMaptoFrequencies.get(temp);
						itemsMaptoFrequencies.put(temp, count + 1);
					} else {
						itemsMaptoFrequencies.put(temp, 1);
					}
				}
			}
		} 
		catch(Exception exc){
			exc.printStackTrace();
		}
        //ordering and elimating non-frequents

        //for breakpoint for comparison
        sortedItemsbyFrequencies.add("null");
        itemsMaptoFrequencies.put("null", 0);
        for (String item : itemsMaptoFrequencies.keySet()) {
            int count = itemsMaptoFrequencies.get(item);
            int i = 0;
            for (String listItem : sortedItemsbyFrequencies) {
                if (itemsMaptoFrequencies.get(listItem) < count) {
                    sortedItemsbyFrequencies.add(i, item);
                    break;
                }
                i++;
            }
        }
        //removing non-frequents
        for (String listItem : sortedItemsbyFrequencies) {
            if (itemsMaptoFrequencies.get(listItem) < threshold) {
                itemstoRemove.add(listItem);
            }
        }
        for (String itemtoRemove : itemstoRemove) {
            sortedItemsbyFrequencies.remove(itemtoRemove);
        }

    }

    private void construct_fpTree(Map<String, Integer> itemsMaptoFrequencies, List<String> sortedItemsbyFrequencies, Vector<String> itemstoRemove) {
        //HeaderTable Creation
        // first elements use just as pointers
        headerTable = new Vector<FPtree>();
        for (String itemsforTable : sortedItemsbyFrequencies) {
            headerTable.add(new FPtree(itemsforTable));
        }
        //FPTree constructing
        //the null node!
        fptree = new FPtree("null");
        fptree.item = null;
        fptree.root = true;
        //ordering frequent items transaction
        
        ResultSet result = DatabaseData.getDatabaseData(this.querry);
		
		try {
			while(result.next()) {
				String line = "";
				for (int i = 0; i < this.column_count; i++) {
					line += result.getString(i+1).replace(" ", "_").replace(",", "") + " ";
				}
				
				StringTokenizer tokenizer = new StringTokenizer(line);
				//System.out.println(line.toString());
	            Vector<String> transactionSortedbyFrequencies = new Vector<String>();
	            int tokenCount = 0;
	            while (tokenizer.hasMoreTokens()) {
	                String item = tokenizer.nextToken();
	                item = this.header[tokenCount].replace(" ", "_") + "=" + item;
	                //System.out.println(this.header[tokenCount].replace(" ", "_") + "=" + item);
	                tokenCount++;
	                if (itemstoRemove.contains(item)) {
	                    continue;
	                }
	                int index = 0;
	                for (String vectorString : transactionSortedbyFrequencies) {
	                    //some lines of condition is for alphabetically check in equals situatioans
	                    if (itemsMaptoFrequencies.get(vectorString) < itemsMaptoFrequencies.get(item) || ((itemsMaptoFrequencies.get(vectorString) == itemsMaptoFrequencies.get(item)) && (vectorString.compareToIgnoreCase(item) < 0 ? true : false))) {
	                        transactionSortedbyFrequencies.add(index, item);
	                        break;
	                    }
	                    index++;
	                }
	                if (!transactionSortedbyFrequencies.contains(item)) {
	                    transactionSortedbyFrequencies.add(item);
	                }
	            }

	            //adding to tree
	            insert(transactionSortedbyFrequencies, fptree, headerTable);
	            transactionSortedbyFrequencies.clear();
			}
		} 
		catch(Exception exc){
			exc.printStackTrace();
		}
		
        //headertable reverse ordering
        //first calculating item frequencies in tree
        for (FPtree item : headerTable) {
            int count = 0;
            FPtree itemtemp = item;
            while (itemtemp.next != null) {
                itemtemp = itemtemp.next;
                count += itemtemp.count;
            }
            item.count = count;
        }
        Comparator<FPtree> c = new frequencyComparitorinHeaderTable();
        Collections.sort(headerTable, c);
    }

    void insert(Vector<String> transactionSortedbyFrequencies, FPtree fptree, Vector<FPtree> headerTable) {
        if (transactionSortedbyFrequencies.isEmpty()) {
            return;
        }
        String itemtoAddtotree = transactionSortedbyFrequencies.firstElement();
        FPtree newNode = null;
        boolean ifisdone = false;
        for (FPtree child : fptree.children) {
            if (child.item.equals(itemtoAddtotree)) {
                newNode = child;
                child.count++;
                ifisdone = true;
                break;
            }
        }
        if (!ifisdone) {
            newNode = new FPtree(itemtoAddtotree);
            newNode.count = 1;
            newNode.parent = fptree;
            fptree.children.add(newNode);
            for (FPtree headerPointer : headerTable) {
                if (headerPointer.item.equals(itemtoAddtotree)) {
                    while (headerPointer.next != null) {
                        headerPointer = headerPointer.next;
                    }
                    headerPointer.next = newNode;
                }
            }
        }
        transactionSortedbyFrequencies.remove(0);
        insert(transactionSortedbyFrequencies, newNode, headerTable);
    }

    private void fpgrowth(FPtree fptree, int threshold, Vector<FPtree> headerTable) {
        frequentPatterns = new HashMap<String, Integer>();
        FPgrowth(fptree, null, threshold, headerTable, frequentPatterns);
    }

    void FPgrowth(FPtree fptree, String base, int threshold, Vector<FPtree> headerTable, Map<String, Integer> frequentPatterns) {
        for (FPtree iteminTree : headerTable) {
            String currentPattern = (base != null ? base : "") + (base != null ? " " : "") + iteminTree.item;
            int supportofCurrentPattern = 0;
            Map<String, Integer> conditionalPatternBase = new HashMap<String, Integer>();
            while (iteminTree.next != null) {
                iteminTree = iteminTree.next;
                supportofCurrentPattern += iteminTree.count;
                String conditionalPattern = null;
                FPtree conditionalItem = iteminTree.parent;

                while (!conditionalItem.isRoot()) {
                    conditionalPattern = conditionalItem.item + " " + (conditionalPattern != null ? conditionalPattern : "");
                    conditionalItem = conditionalItem.parent;
                }
                if (conditionalPattern != null) {
                    conditionalPatternBase.put(conditionalPattern, iteminTree.count);
                }
            }
            frequentPatterns.put(currentPattern, supportofCurrentPattern);
            //counting frequencies of single items in conditional pattern-base
            Map<String, Integer> conditionalItemsMaptoFrequencies = new HashMap<String, Integer>();
            for (String conditionalPattern : conditionalPatternBase.keySet()) {
                StringTokenizer tokenizer = new StringTokenizer(conditionalPattern);
                while (tokenizer.hasMoreTokens()) {
                    String item = tokenizer.nextToken();
                    if (conditionalItemsMaptoFrequencies.containsKey(item)) {
                        int count = conditionalItemsMaptoFrequencies.get(item);
                        count += conditionalPatternBase.get(conditionalPattern);
                        conditionalItemsMaptoFrequencies.put(item, count);
                    } else {
                        conditionalItemsMaptoFrequencies.put(item, conditionalPatternBase.get(conditionalPattern));
                    }
                }
            }
            //conditional fptree
            //HeaderTable Creation
            // first elements are being used just as pointers
            // non conditional frequents also will be removed
            Vector<FPtree> conditional_headerTable = new Vector<FPtree>();
            for (String itemsforTable : conditionalItemsMaptoFrequencies.keySet()) {
                int count = conditionalItemsMaptoFrequencies.get(itemsforTable);
                if (count < threshold) {
                    continue;
                }
                FPtree f = new FPtree(itemsforTable);
                f.count = count;
                conditional_headerTable.add(f);
            }
            FPtree conditional_fptree = conditional_fptree_constructor(conditionalPatternBase, conditionalItemsMaptoFrequencies, threshold, conditional_headerTable);
            //headertable reverse ordering
            Collections.sort(conditional_headerTable, new frequencyComparitorinHeaderTable());
            //
            if (!conditional_fptree.children.isEmpty()) {
                FPgrowth(conditional_fptree, currentPattern, threshold, conditional_headerTable, frequentPatterns);
            }
        }
    }

    private void insert(Vector<String> pattern_vector, int count_of_pattern, FPtree conditional_fptree, Vector<FPtree> conditional_headerTable) {
        if (pattern_vector.isEmpty()) {
            return;
        }
        String itemtoAddtotree = pattern_vector.firstElement();
        FPtree newNode = null;
        boolean ifisdone = false;
        for (FPtree child : conditional_fptree.children) {
            if (child.item.equals(itemtoAddtotree)) {
                newNode = child;
                child.count += count_of_pattern;
                ifisdone = true;
                break;
            }
        }
        if (!ifisdone) {
            for (FPtree headerPointer : conditional_headerTable) {
                //this if also gurantees removing og non frequets
                if (headerPointer.item.equals(itemtoAddtotree)) {
                    newNode = new FPtree(itemtoAddtotree);
                    newNode.count = count_of_pattern;
                    newNode.parent = conditional_fptree;
                    conditional_fptree.children.add(newNode);
                    while (headerPointer.next != null) {
                        headerPointer = headerPointer.next;
                    }
                    headerPointer.next = newNode;
                }
            }
        }
        pattern_vector.remove(0);
        insert(pattern_vector, count_of_pattern, newNode, conditional_headerTable);
    }

    private void print_solution() {
        for (String frequentPattern : frequentPatterns.keySet()) {
        	int key_len = frequentPattern.split(" ").length;
        	if (key_len == 1) continue;
        	
            System.out.println(frequentPattern + " " + frequentPatterns.get(frequentPattern));
        }
    }
    
    public ArrayList<String> returnResult() {
    	ArrayList<String> result = new ArrayList<String>();
    	
        for (String frequentPattern : frequentPatterns.keySet()) {
        	int key_len = frequentPattern.split(" ").length;
        	if (key_len == 1) continue;
        	
            result.add(frequentPattern + " " + frequentPatterns.get(frequentPattern));
        }
        
        return result;
    }

}
