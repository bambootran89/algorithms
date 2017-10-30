package Hash;

public class ModInverse {

	public Long gca(Long a, Long b){
		if (a==0)
			return b;
		return gca(b%a,a);
	}
	
	public Long power(Long x, Long y, Long m){
		if (y==0)
			return (long)1;
		Long p = power(x, y/2, m)%m;
		p = (p * p) % m;
		return (y%2==0)?p:(p*x)%m;
	}
	
	public Long modInverse(Long a, Long m){
		if (gca(a,m) == 1){
			return power(a, m-2, m);
		}else {
			throw new RuntimeException("Can not do modInverse");
		}
	}
}
