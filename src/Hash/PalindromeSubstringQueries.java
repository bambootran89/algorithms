package Hash;

public class PalindromeSubstringQueries {

	static long p = 200;
	static long mod = 1000000007;
	private static class Query{
		public int L;
		public int R;
		public Query(int i_, int j_){
			L = i_;
			R = j_;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abaaabaaaba";
		Long[] prefix = new Long[str.length()+1];
		Long[] suffix = new Long[str.length()+1];
		Long[] power = new Long[str.length()+1];
		computePowers(power, str.length());
		computePrefixHash(str, str.length(), prefix, power);
		computeSuffixHash(str, str.length(), suffix, power);		
		Query q[] = {new Query(0, 10), new Query(5, 8), new Query(2, 5), new Query(5, 9)};
		int m = q.length;
		queryResults(str, q, m, str.length(), prefix, suffix, power);
	
	}
	
	
	private static void queryResults(String str, Query[] q, int m, int length, Long[] prefix, Long[] suffix,
			Long[] power) {
		ModInverse modInv = new ModInverse();
		for (int i=0; i<=m-1; i++)
	    {
	        int L = q[i].L;
	        int R = q[i].R;
	        
	        // Hash Value of Substring [L,R]
	        long hash_LR = ((prefix[R+1]- prefix[L]+mod)%mod *modInv.modInverse(power[L],mod )%mod)%mod;
	        // Reverse Hash Value of Substring [L,R]
	        long reverse_hash_LR = ((suffix[length-L]-suffix[length-R-1]+mod)%mod *modInv.modInverse(power[length-R-1],mod)%mod)%mod;
	        
	        // If both are equal then the substring is a palindrome
	        if (hash_LR==(reverse_hash_LR) )
	        {
	            
	        	if (isPalindrome(str, L, R) == true)
	                System.out.println("The Substring [ " + L + "," + R + " ] is a " + "palindrome\n");
	            else
	            	System.out.println("The Substring [ " + L + "," + R + " ] is not a " + "palindrome\n");
	        }
	 
	        else
	        	System.out.println("The Substring [ " + L + "," + R + " ] is not a " + "palindrome\n");
	    }
	}


	private static boolean isPalindrome(String str, int L, int R) {
		while (R > L)
	        if (str.charAt(L++) != str.charAt(R--))
	            return false;
	    return true;
	}


	private static void computeSuffixHash(String str, int length, Long[] suffix, Long[] power) {
		suffix[0] = (long)0;
		suffix[1] = (long)(str.charAt(length-1));
		for (int j=2; j<length+1; j++){
			suffix[j] = (suffix[j-1]%mod + ((long)(str.charAt(length-j))%mod * power[j-1]%mod)%mod )%mod;
		}
		
	}

	private static void computePrefixHash(String str, int length, Long[] prefix, Long[] power) {
		prefix[0]= (long)0;
		prefix[1]=(long)(str.charAt(0));
		for(int i = 2; i<str.length()+1; i++){
			prefix[i] = (prefix[i-1]%mod + ((long)(str.charAt(i-1))%mod * power[i-1]%mod)%mod)%mod;
		}		
	}

	private static void computePowers(Long[] power, int length) {
		power[0] = (long)1;
		for (int i = 1; i<=length;i++)
			power[i] = ((power[i-1]%mod)*(p%mod))%mod;
		
	}
	
	
	

}
