import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		// open file input stream
		BufferedReader reader = new BufferedReader(new FileReader("spam.csv"));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;
		ArrayList<Message> empList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			Message emp = new Message();
			scanner = new Scanner(line);
			scanner.useDelimiter(",,,,");
			while (scanner.hasNext()) {
				String data = scanner.next();
				if(index==0)
					emp.setId(data);
				else if (index == 1)
					emp.setName(data);
				else
					System.out.println("invalid data::" + data);
				index++;
			}
			index = 0;
			empList.add(emp);
		}
		
		//close reader
		reader.close();
		
		//System.out.println(empList);
		int spam=0,ham=0;
		
		ArrayList<Table> table = new ArrayList<>();
		for(int i=0;i<empList.size();i++)
		{
			Message temp = empList.get(i);
			String demo = temp.getId();
			Table temp1 = new Table();
			if(demo.startsWith("spam,"))
			{
				temp1.setId(1);
				temp1.setText(demo.substring(5, demo.length()-3).toLowerCase());
				spam++;
			}
			else if(demo.startsWith("ham,"))
			{
				temp1.setId(0);
				temp1.setText(demo.substring(4, demo.length()-3).toLowerCase());
				ham++;
			}
			table.add(temp1);
		}
		
		
		
/*		for(int i=0;i<table.size();i++){
			Table temp = table.get(i);
			System.out.println(temp);
			String demo = temp.getText();
			StopWords sw = new StopWords();
			String demo2 = sw.removeChar("I like Music!!");
			System.out.println(demo2);
			
		}*/
//		System.out.println(table);
		
		StopWords sw = new StopWords();
		String demo2 = sw.removeChar("I like Music!");
		System.out.println(demo2);
		System.out.println("spam is : " + spam + "\n ham is : " + ham );
		String check = sw.removeStopWords("Prageet has a big dick won't wouldn wouldn't i me myself are is am during deepesh before");
		System.out.println(check);
		
		for(int i=0;i<table.size();i++){
			int label = table.get(i).getLabel();
			String text = table.get(i).getText();
			if(text!=null){
				text = sw.removeChar(text);
			//	System.out.println("text is -->" + text);
				text = sw.removeStopWords(text);
			//	System.out.println("text is -->" + text);
				table.get(i).setText(text);
			}
		}
		
		
		
/*		Table tt = new Table();
		tt.setId(1);
		tt.setText("Prageet is most Awesome Person In the World Prageet");
		ArrayList<Table> arr = new ArrayList<>();
		arr.add(tt);
		System.out.println(tt);*/
		
		Stemmer st=new  Stemmer();
		ArrayList<String> tok = new ArrayList<String>();
        String[] tokens = {"normalize","technical","education","corresponding","available"};
        for (String x: tokens){
            tok.add(x);
        }
		System.out.println(st.completeStem(tok));
		String nn = table.get(1).getText();
		System.out.println(nn);
		String temp[] = nn.split(" ");
	//	for(int i=0;i<temp.length;i++)
	//		System.out.println(temp[i] + "    " + temp[i].length());
		
		ArrayList<Table> newTable = new ArrayList<>();
		newTable = st.complete(table);
		
		for(int i=0;i<newTable.size();i++){
	//		System.out.println(newTable.get(i).getLabel() + "::::"  + newTable.get(i).getText());
		}
		
		HashMap<String, Integer> mapAll = new HashMap<>();
		HashMap<String, Integer> mapSpam = new HashMap<>();
		HashMap<String, Integer> mapHam = new HashMap<>();		
		Tokenization token = new Tokenization();
		mapAll = token.tokenAll(newTable);
//		System.out.println(mapAll);
		mapSpam = token.tokenSpam(newTable);
//		System.out.println(mapSpam);
		mapHam = token.tokenHam(newTable);
//		System.out.println(mapHam);
		Set<String> ss = mapAll.keySet();
		System.out.println(ss);
		
		
		
	}
	
}



		
