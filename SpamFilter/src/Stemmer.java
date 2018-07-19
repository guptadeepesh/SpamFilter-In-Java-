import java.util.ArrayList;
import java.util.List;
import java.util.*;

class NewString {
  public String str;
  		NewString() {
  		str = "";
  		}
  }

public class Stemmer {
	String Clean( String str ) {
	     int last = str.length();
	     new Character( str.charAt(0) );
	     String temp = "";
	     for ( int i=0; i < last; i++ ) {
	         if ( Character.isLetterOrDigit( str.charAt(i) ) )
	            temp += str.charAt(i);
	     	}
	     return temp;
	  } //clean

boolean hasSuffix( String word, String suffix, NewString stem ) {
		String tmp = "";
		if ( word.length() <= suffix.length() )
	        return false;
	    if (suffix.length() > 1) 
	        if ( word.charAt( word.length()-2 ) != suffix.charAt( suffix.length()-2 ) )
	           return false;

	     	stem.str = "";
	     	for ( int i=0; i<word.length()-suffix.length(); i++ )
	     			stem.str += word.charAt( i );
	     	tmp = stem.str;

	     	for ( int i=0; i<suffix.length(); i++ )
	     		tmp += suffix.charAt( i );

	     	if ( tmp.compareTo( word ) == 0 )
	     		return true;
	     	else
	        return false;
	  }

boolean vowel( char ch, char prev ) {
	    switch ( ch ) {
	        case 'a': case 'e': case 'i': case 'o': case 'u': 
	    return true;
	        case 'y': {
	    switch ( prev ) {
	    	case 'a': case 'e': case 'i': case 'o': case 'u': 
	              return false;
	    default: 
	              return true;
	          }
	        }
	    default : 
	          return false;
	     }
	  }
	int measure( String stem ) {
		int i=0, count = 0;
	    int length = stem.length();
	    while ( i < length ) {
	       for ( ; i < length ; i++ ) {
	           if ( i > 0 ) {
	              if ( vowel(stem.charAt(i),stem.charAt(i-1)) )
	                 break;
	           }
	           else {  
	              if ( vowel(stem.charAt(i),'a') )
	            break; 
	       }
	   }

	   for ( i++ ; i < length ; i++ ) {
	       if ( i > 0 ) {
	          if ( !vowel(stem.charAt(i),stem.charAt(i-1)) )
	              break;
	          }
	       else {  
	          if ( !vowel(stem.charAt(i),'?') )
	             break;
	       }
	   } 
	  if ( i < length ) {
	     count++;
	     i++;
	  }
	} //while

	    return(count);
	  }

	  boolean containsVowel( String word ) {

	     for (int i=0 ; i < word.length(); i++ )
	         if ( i > 0 ) {
	            if ( vowel(word.charAt(i),word.charAt(i-1)) )
	               return true;
	         }
	         else {  
	            if ( vowel(word.charAt(0),'a') )
	               return true;
	         }

	     return false;
	  }

	  boolean cvc( String str ) {
	     int length=str.length();

	     if ( length < 3 )
	        return false;

	     if ( (!vowel(str.charAt(length-1),str.charAt(length-2)) )
	        && (str.charAt(length-1) != 'w') && (str.charAt(length-1) != 'x') && (str.charAt(length-1) != 'y')
	&& (vowel(str.charAt(length-2),str.charAt(length-3))) ) {

	if (length == 3) {
	   if (!vowel(str.charAt(0),'?')) 
	              return true;
	           else
	              return false;
	        }
	        else {
	           if (!vowel(str.charAt(length-3),str.charAt(length-4)) ) 
	              return true; 
	           else
	              return false;
	        } 
	     }   

	     return false;
	  }

	  String step1( String str ) {
		  NewString stem = new NewString();
		  if ( str.charAt( str.length()-1 ) == 's' ) {
			  if ( (hasSuffix( str, "sses", stem )) || (hasSuffix( str, "ies", stem)) ){
				  String tmp = "";
				  for (int i=0; i<str.length()-2; i++)
					  tmp += str.charAt(i);
				  str = tmp;
			  }
		  else {
			  if ( ( str.length() == 1 ) && ( str.charAt(str.length()-1) == 's' ) ) {
				  str = "";
			  return str;
			  }
			  if ( str.charAt( str.length()-2 ) != 's' ) {
				  String tmp = "";
				  for (int i=0; i<str.length()-1; i++)
					  tmp += str.charAt(i);
				  str = tmp;
			  }
		  	}  
		  }
		  if ( hasSuffix( str,"eed",stem ) ) {
			  if ( measure( stem.str ) > 0 ) {
				  String tmp = "";
				  	for (int i=0; i<str.length()-1; i++)
				  		tmp += str.charAt( i );
				  	str = tmp;
			  		}
		  		}
		  else {  
			  if (  (hasSuffix( str,"ed",stem )) || (hasSuffix( str,"ing",stem )) ) { 
				  if (containsVowel( stem.str ))  {
					  String tmp = "";
					  	for ( int i = 0; i < stem.str.length(); i++)
					  		tmp += str.charAt( i );
					  	str = tmp;
					  	if ( str.length() == 1 )
					  		return str;
					  	if ( ( hasSuffix( str,"at",stem) ) || ( hasSuffix( str,"bl",stem ) ) || ( hasSuffix( str,"iz",stem) ) ) {
					  		str += "e";
					  		}
				else {   
					int length = str.length(); 
					if ( (str.charAt(length-1) == str.charAt(length-2)) && (str.charAt(length-1) != 'l') && (str.charAt(length-1) != 's') && (str.charAt(length-1) != 'z') ) {
							tmp = "";
							for (int i=0; i<str.length()-1; i++)
								tmp += str.charAt(i);
							str = tmp;
					}
					else
						if ( measure( str ) == 1 ) {
							if ( cvc(str) ) 
								str += "e";
							}
					}
	       }
	    }
	 }
		  if ( hasSuffix(str,"y",stem) ) 
			  if ( containsVowel( stem.str ) ) {
				  String tmp = "";
				  for (int i=0; i<str.length()-1; i++ )
					  tmp += str.charAt(i);
				  str = tmp + "i";
			  	}
		  	return str;  
	  }


String step2( String str ) {

	     String[][] suffixes = { { "ational", "ate" },
	                            { "tional",  "tion" },
	                            { "enci",    "ence" },
	                            { "anci",    "ance" },
	                            { "izer",    "ize" },
	                            { "iser",    "ize" },
	                            { "abli",    "able" },
	                            { "alli",    "al" },
	                            { "entli",   "ent" },
	                            { "eli",     "e" },
	                            { "ousli",   "ous" },
	                            { "ization", "ize" },
	                            { "isation", "ize" },
	                            { "ation",   "ate" },
	                            { "ator",    "ate" },
	                            { "alism",   "al" },
	                            { "iveness", "ive" },
	                            { "fulness", "ful" },
	                            { "ousness", "ous" },
	                            { "aliti",   "al" },
	                            { "iviti",   "ive" },
	                            { "biliti",  "ble" }};
	     NewString stem = new NewString();


	     for ( int index = 0 ; index < suffixes.length; index++ ) {
	         if ( hasSuffix ( str, suffixes[index][0], stem ) ) {
	            if ( measure ( stem.str ) > 0 ) {
	               str = stem.str + suffixes[index][1];
	               return str;
	            }
	         }
	     }
	     return str;
	  }

	 
String step3( String str ) {
		   String[][] suffixes = { { "icate", "ic" },
	                               { "ative", "" },
	                               { "alize", "al" },
	                               { "alise", "al" },
	                               { "iciti", "ic" },
	                               { "ical",  "ic" },
	                               { "ful",   "" },
	                               { "ness",  "" }};
	        NewString stem = new NewString();

	        for ( int index = 0 ; index<suffixes.length; index++ ) {
	            if ( hasSuffix ( str, suffixes[index][0], stem ))
	               if ( measure ( stem.str ) > 0 ) {
	                  str = stem.str + suffixes[index][1];
	                  return str;
	               }
	        }
	        return str;
	  }

String step4( String str ) {

	     String[] suffixes = { "al", "ance", "ence", "er", "ic", "able", "ible", "ant", "ement", "ment", "ent", "sion", "tion",
	                   "ou", "ism", "ate", "iti", "ous", "ive", "ize", "ise"};

	     NewString stem = new NewString();

	     for ( int index = 0 ; index<suffixes.length; index++ ) {
	         if ( hasSuffix ( str, suffixes[index], stem ) ) {

	            if ( measure ( stem.str ) > 1 ) {
	               str = stem.str;
	               return str;
	            }
	         }
	     }
	     return str;
	  }

String step5( String str ) {

	     if ( str.charAt(str.length()-1) == 'e' ) { 
	    	 if ( measure(str) > 1 ) {/* measure(str)==measure(stem) if ends in vowel */
	    		 	String tmp = "";
	    			for ( int i=0; i<str.length()-1; i++ ) 
	    				tmp += str.charAt( i );
	    			str = tmp;
	    	 }
	    else
	    	if ( measure(str) == 1 ) {
	    		String stem = "";
	    		for ( int i=0; i<str.length()-1; i++ ) 
	              stem += str.charAt( i );
	    		if ( !cvc(stem) )
	             str = stem;
	    	}
	     }

	 if ( str.length() == 1 )
	    return str;
	 if ( (str.charAt(str.length()-1) == 'l') && (str.charAt(str.length()-2) == 'l') && (measure(str) > 1) )
		 if ( measure(str) > 1 ) {/* measure(str)==measure(stem) if ends in vowel */
			 String tmp = "";
	         for ( int i=0; i<str.length()-1; i++ ) 
	               tmp += str.charAt( i );
	         str = tmp;
	        } 
	     return str;
	  }

	 String stripPrefixes ( String str) {

	     String[] prefixes = { "kilo", "micro", "milli", "intra", "ultra", "mega", "nano", "pico", "pseudo"};

	 int last = prefixes.length;
	 for ( int i=0 ; i<last; i++ ) {
	     if ( str.startsWith( prefixes[i] ) ) {
	        String temp = "";
	            for ( int j=0 ; j< str.length()-prefixes[i].length(); j++ )
	                temp += str.charAt( j+prefixes[i].length() );
	            return temp;
	         }
	     }

	     return str;
	  }

private String stripSuffixes( String str ) {

	     str = step1( str );
	     if ( str.length() >= 1 )
	        str = step2( str );
	     if ( str.length() >= 1 )
	        str = step3( str );
	     if ( str.length() >= 1 )
	        str = step4( str );
	     if ( str.length() >= 1 )
	        str = step5( str );

	     return str; 
	  }


	  public String stripAffixes( String str ) {

	    str = str.toLowerCase();
	    str = Clean(str);

	    if (( str != "" ) && (str.length() > 2)) {
	   str = stripPrefixes(str);

	   if (str != "" ) 
	      str = stripSuffixes(str);

	}   

	return str;
	} //stripAffixes
	 
	  
	  public ArrayList<String> completeStem(List<String> tokens1){
	        Stemmer pa = new Stemmer();
	        ArrayList<String> arrstr = new ArrayList<String>();
	        for (String i : tokens1){
	            String s1 = pa.step1(i);
	            String s2 = pa.step2(s1);
	            String s3= pa.step3(s2);
	            String s4= pa.step4(s3);
	            String s5= pa.step5(s4);
	            arrstr.add(s5);
	        }
	        return arrstr;
	    }
	  
	 /* public ArrayList<Table> complete(ArrayList<Table> table){
		  
		  Stemmer pa = new Stemmer();
		  ArrayList<Table> stemTable = new ArrayList<>();
		  for(int i=0;i<table.size();i++){
			  String str = table.get(i).getText();
			  int label = table.get(i).getLabel();
			//  System.out.println(str);
				if(str!=null){
				String temp[] = str.split(" ");
				String temp2 = "";
				Table temp1 = new Table();
					for(int j=0;j<temp.length;j++){	
					if(temp[j].length()!=0){
					//	System.out.println(pa.step5(pa.step4(pa.step3(pa.step2(pa.step1(temp[j]))))));
					//	String temp3 = pa.step5(pa.step4(pa.step3(pa.step2(pa.step1(temp[j])))));
					//	temp2 += temp3 + " ";
						for (String demo : temp){
				            String s1 = pa.step1(demo);
				            String s2 = pa.step2(s1);
				            String s3= pa.step3(s2);
				            String s4= pa.step4(s3);
				            String s5= pa.step5(s4);
				            temp2 += s5 + " ";
				        }
						}
					}
					if(label==1){
						temp1.setId(1);
						temp1.setText(temp2);
						stemTable.add(temp1);
					}
					else if(label==0){
						temp1.setId(0);
						temp1.setText(temp2);
						stemTable.add(temp1);
					}
				}
		  }  
		  return stemTable;
	  }
	*/
	  
	  public ArrayList<Table> complete(ArrayList<Table> table){
		  
		  ArrayList<Table> newTable = new ArrayList<>();
		  Stemmer pa = new Stemmer();
		  for(int i=0;i<table.size();i++){
			  String text = table.get(i).getText();
			  int label = table.get(i).getLabel();
			  if(text!=null){
				  String newText = "";
				  String token[] = text.split(" ");
				  for(int j=0;j<token.length;j++){
					if(token[j].length()!=0){
						String s1 = pa.step1(token[j]);
			            String s2 = pa.step2(s1);
			            String s3= pa.step3(s2);
			            String s4= pa.step4(s3);
			            String s5= pa.step5(s4);
			        //    System.out.println("s5 is :::: " + s5 );
			            newText += s5 + " ";
			            
					}
				  }
				  //System.out.println("new Text is :::: " + newText);
				  Table temp = new Table();
				  temp.setText(newText);
				  temp.setId(label);
				  System.out.println(temp.getLabel() + "::::" + temp.getText());
				  newTable.add(temp);
			  }
		  }
		  
		  
		  return newTable;
		  
	  }
}
