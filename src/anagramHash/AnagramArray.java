package anagramHash;

import java.util.Arrays;

public class AnagramArray {
	
	Word_Key wordAndKeys[];
	int size;
	int elementCount;
	
	
	//declares an anagram array of Word_Key objects of size of number of words in dictionary
	AnagramArray(int size){
		wordAndKeys = new Word_Key[size];
		this.size = size;
		elementCount=0;
	}
	
	//Adds word_key tuple object to wordsAndKeys array and increments index to determine where to add next value.
	public void addWord(String word){
		wordAndKeys[elementCount]=new Word_Key(word);
		elementCount++;
	}
	
	//Calls sorts all words by key value.
	public void sort(){
		wordAndKeys=mergeSortByKeys(wordAndKeys);
	}
	
	//Applies the merge sort by sorting the array of Key/Word pairs based off of the keys of the values.
	//(Same structure as Word_Key class merge sort except adjusted to sort arrays of key/word pairs
	//rather than arrays of chars.)
	private Word_Key[] mergeSortByKeys(Word_Key[] x){
		int n = x.length;
		
		if(n==1||n==0) return x;
		
		Word_Key left[] = Arrays.copyOfRange(x, 0, n/2);
		Word_Key right[] = Arrays.copyOfRange(x, n/2, n);
		
		left = mergeSortByKeys(left);
		right = mergeSortByKeys(right);
		
		return merge(left,right);
	}

	//Applies the merge by appending the lowest values of the two arrays of key/word pairs based off the
	//key value.
	//(Same structure as Word_Key class merge sort except adjusted to sort arrays of key/word pairs
	//rather than arrays of chars.)
	private Word_Key[] merge(Word_Key[] l, Word_Key[] r){
		
		Word_Key combined[]=new Word_Key[l.length+r.length];
		
		int index=0;
		int leftIndex=0;
		int rightIndex=0;
		
		while(leftIndex<l.length&&rightIndex<r.length){
			String left = l[leftIndex].key;
			String right = r[rightIndex].key;
			if(left.compareTo(right)>0){
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

		return combined;
		
	}

	/*The purpose of this method is to take an array sorted by key values of the words (where key values are
	 * a word's letters sorted alphabetically) and checks if the next value has the same key value as the current one.
	 * If the key value is the same we can assume that the strings are anagrams, if the keys are different then we return 
	 * all the anagrams we found up to that point and then start a new search for the next consecutive set of anagrams.
	 * 
	 */
	public String[] returnAnagramSets(){
		
		//Empty String array to help us organize all sets of anagrams. 
		String anagrams[]=new String[size];
		//index value for which anagram is being added.
		int count = 0;
		//index value for which index of key/word pair is being accessed.
		int index=1;
		
		/*This next part does what was described above which is that it checks successive sorted values by their key
		 * starting with index 0 and adds it to the current anagram class. If the key doesn't match a particular value then
		 * the anagrams stored in currClass up until that point are stored at anagram[count] and then the new found key value 
		 * is tested for anagrams. This is repeated until all dictionary words have been checked.
		 * 
		 */
		String currClass = wordAndKeys[0].word;
		String currKey = wordAndKeys[0].key;
		while(index<size){
			if(currKey.equals(wordAndKeys[index].key)){
				currClass+=(" "+wordAndKeys[index].word);
				index++;
			}
			else{
				anagrams[count]=new String(currClass+"\n");
				count++;
				currClass=wordAndKeys[index].word;
				currKey=wordAndKeys[index].key;
				index++;
			}	
		}
		return anagrams;
	}
}
