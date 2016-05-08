package solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Runner {
	private static final String D_condition = "[():,]+";
	private static final String D_space = "[ ]+";
	private static final String D_result = "[()]+";
	private static final String D_fact = "[() ]+";
	
	private static ArrayList<Rule> rules;
	private static ArrayList<Fact> facts;
	
	public static void main(String[] args) {
		try {
		rules = initRules();
		facts = initFacts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * pre kazde pravidlo
		 * 	 		naviazat 
		 * 			
		 * 			pre kazde naviazanie
		 * 				naviazat premenne k potom
		 * 				ulozit ako potencionalne apk instanciu
		 * 			end
		 * 
		 * end
		 */
		
		
	}
	
	
	private static ArrayList<Rule> initRules() throws Exception {
		FileReader fr = new FileReader("files/rules.txt");
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<Rule> t_rules = new ArrayList<>();
		String[] tmp, tmp_r;
		String name="", cond="", res="";
		Condition c; ResultFact rf; Rule rule;
		
		while ((name = br.readLine()) != null ) { //turns text into objects
			cond = br.readLine();
			res = br.readLine();
			
			rule = new Rule(name);
			tmp = cond.split(D_condition); //splits certain conditions on lines and then for each line separates words
		    tmp_r = res.split(D_result);
			
		    for (int i = 1; i < tmp_r.length; i++) {
		    	rf = new ResultFact(tmp_r[i].split(D_space));
		    	rule.addResultFact(rf);
		    }
		    
			for (int i = 1; i < tmp.length; i++) {
				c = new Condition(tmp[i].split(D_space));
				rule.addCondition(c);
			}
			
			t_rules.add(rule);
			name = br.readLine(); //emptyLine
		}
		br.close();
		fr.close();
		
		return t_rules;
	}

	private static ArrayList<Fact> initFacts() throws Exception {
		FileReader fr = new FileReader("files/facts.txt");
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<Fact> t_facts = new ArrayList<>();
		String[] tmp;
		String line_f = "";
		Fact fact;
		
		while ((line_f = br.readLine()) != null ) {
			tmp = line_f.split(D_fact);
			tmp = Functions.shiftRight(tmp);
			fact = new Fact(tmp);
			t_facts.add(fact);
		}
		
		br.close();
		fr.close();
		
		return t_facts;
	}
}
