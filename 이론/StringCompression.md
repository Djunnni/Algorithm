### 스트링 압축 예시

```java

/**
 * String 압축 예
 * @author djunnni
 *
 */
public class StringCompression {
	public static void main(String[] args) {
		String GALAXY = "GALAXY";
		int value = compression(GALAXY.toCharArray());
		
		System.out.println(Integer.toBinaryString(value));
		System.out.println(value);
	}
	public static int compression(char[] str) {
		int res = 0;
		for(int i = 0, size = str.length; i < size; i++) {
			res = (res << 5) | (str[i] ^ 64);
		}
		
		return res;
	}
}


```