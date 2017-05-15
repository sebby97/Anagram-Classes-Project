package anagramHash;

import java.io.*;
//This is essentially the driver class that interacts with the arguments to read in a dictionary and 
//output the anagram classes to a different file using buffered readers and writers

public class AnagramClassFinder {
	public static void main(String[] args) throws IOException {
		//Instance of anagram classes that will be operated on
		AnagramArray anagramArr;
		
		//Buffered reader to take input from file. This one is calculating needed size of array
		BufferedReader dictionaryCounter = new BufferedReader(new FileReader(args[0]));
		String entry;
		int count = 0;
		while (( entry = dictionaryCounter.readLine()) != null) {
			count++;
		}
		dictionaryCounter.close();

		//Creates new anagram array which is of size of number of elements in dict. 
		anagramArr=new AnagramArray(count);
		//Buffered reader to take input from file. This one reads and adds words into anagram array class
		BufferedReader dictionaryReader = new BufferedReader(new FileReader(args[0]));
		while (( entry = dictionaryReader.readLine()) != null) {
			anagramArr.addWord(entry);
		}
		dictionaryReader.close();
		
		//After all anagrams are in the array, they are sorted.
		anagramArr.sort();
		
		//Rather than leaving the anagrams in the array, consecutive anagrams are checked for matching keys and 
		//Combined into one line
		String anagrams[] = anagramArr.returnAnagramSets();
		
		
		PrintWriter pw = new PrintWriter(new FileWriter(args[1]));
		
		int index=0;
		while(anagrams[index]!=null){
			pw.write(anagrams[index]);
			index++;
			
		}
		pw.close();
		
	
	
	}
}
