import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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
		
		System.out.println(empList);
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
		
		System.out.println(table);
		
		System.out.println("spam is : " + spam + "\n ham is : " + ham );
		
	}
	

}
