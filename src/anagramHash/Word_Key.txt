package anagramHash;

import java.util.Arrays;

public class Word_Key {
	String key;
	String word;
	
	//Makes new key_word object which contains a word value and its key value.
	public Word_Key(String word){
		this.word=word;
		key = keyFunc(word);
	}
	
	public Word_Key(String word, String key)
	{
		this.word=word;
		this.key=key;
	}

	//returns key value of this instance of Word_Key object.
	public String getKey(){
		return key;
	}
	
	//Creates a key which is sorted letters.
	public String keyFunc(String entry){
		//Creates an array of characters from the entry word for sorting.
		char word[] = entry.toCharArray();	
		//New character array which is the sort applied to the original array of characters
		char wordSorted[] = mergeSort(word);
		//returns sorted characters as a String.
		return new String(wordSorted);
	}

	//key function helper which is a Merge Sort which sorts all strings based on their alphabetically ordered letters.
	private char[] mergeSort(char x[]){
		int n = x.length;								//Gets length of the current array
		
		if(n==1||n==0) return x;						//If current array is size 1 or 0, it is trivially sorted.
		
		char left[] = Arrays.copyOfRange(x, 0, n/2);	//Divide into left and right arrays at n/2 for dividing and conquering.
		char right[] = Arrays.copyOfRange(x, n/2, n);
		
		left = mergeSort(left);							//Recursively mergeSort the left and right character arrays. 
		right = mergeSort(right);
		
		return merge(left,right);						//Merge the two arrays so that they are sorted.
	}
	
	//Merge method which 
	private char[] merge(char l[],char r[]){
		
		//New char array that will be returned which will consist of merge sorted left and right arrays
		char combined[]=new char[l.length+r.length];
		
		//index at which next character needs to be added 
		int index=0;
		
		//determines the index of which indexed value from left and right should be decided on to be merged next so that 
		//we don't have to shift over arrays to extract from index 0.
		int leftIndex=0;
		int rightIndex=0;
		
		//While both of them have values to enter insert whichever has a smaller value at the front.
		while(leftIndex<l.length&&rightIndex<r.length){
			if(l[leftIndex]>=r[rightIndex]){
				combined[index]=r[rightIndex];
				index++;
				rightIndex++;
			}
			else{
				combined[index]=l[leftIndex];
				index++;
				leftIndex++;
			}
		}
		
		//Once either is empty, just dump the rest of the values into the combined array
		while(leftIndex<l.length){
			combined[index]=l[leftIndex];
			index++;
			leftIndex++;
		}
		while(rightIndex<r.length){
			combined[index]=r[rightIndex];
			index++;
			rightIndex++;
		}
		//return merge sorted array.
		return combined;
		
	}
}
