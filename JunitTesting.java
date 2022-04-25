package Default;

public class JunitTesting {
	
	public int square(int x) {
		return x*x;
	}
	
	public int CountA(String word) {
		int count = 0;
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i)=='a' || word.charAt(i)=='A') {
				count++;
			}
		}
		return count;
	}
}