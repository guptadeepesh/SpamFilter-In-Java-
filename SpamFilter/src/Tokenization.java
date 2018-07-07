import java.util.ArrayList;
import java.util.HashMap;

public class Tokenization {
	
	public HashMap<String, Integer> tokenAll(ArrayList<Table> table){
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<table.size();i++){	
			String str = table.get(i).getText();
			if(str!=null){
			String temp[] = str.split(" ");
				for(int j=0;j<temp.length;j++){	
					if (map.containsKey(temp[j])) {
						Integer a = map.get(temp[j]);
						a++;
						map.put(temp[j], a);
					}
					else{
						map.put(temp[j], 1);
					}
				}	
			}
		}
		return map;
	}
	
	public HashMap<String, Integer> tokenSpam(ArrayList<Table> table){
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<table.size();i++){
			if(table.get(i).getLabel()==1){
				String str = table.get(i).getText();
				if(str!=null){
				String temp[] = str.split(" ");
					for(int j=0;j<temp.length;j++){	
						if (map.containsKey(temp[j])) {
							Integer a = map.get(temp[j]);
							a++;
							map.put(temp[j], a);
						}
						else{
							map.put(temp[j], 1);
						}
					}	
				}
			}
		}
		return map;
	}
	
	public HashMap<String, Integer> tokenHam(ArrayList<Table> table){
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<table.size();i++){
			if(table.get(i).getLabel()==0){
				String str = table.get(i).getText();
				if(str!=null){
				String temp[] = str.split(" ");
					for(int j=0;j<temp.length;j++){	
						if (map.containsKey(temp[j])) {
							Integer a = map.get(temp[j]);
							a++;
							map.put(temp[j], a);
						}
						else{
							map.put(temp[j], 1);
						}
					}	
				}
			}
		}
		return map;
	}

}
