import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;


public class MainC {

	private static ArrayList<String> fairList = new ArrayList<String>();
	
	public static void main(String[] args) {
		FileReader ir;
		
		try {
			ir = new FileReader("src/C-large-practice-2.in");
			BufferedReader b = new BufferedReader(ir);
			String s;
			s = b.readLine();
			int num = Integer.valueOf(s);
			
			FileWriter fw = new FileWriter("src/C-answer.txt");
			long start = System.currentTimeMillis();

			String MAX = new BigInteger("10").pow(100).toString();
			fairList.add("1");
			fairList.add("4");
			fairList.add("9");
			generateKaibun("",MAX);
			generateKaibun("0",MAX);
			generateKaibun("1",MAX);
			generateKaibun("2",MAX);
			generateKaibun("3",MAX);
			
			
			String sA,sB;
			String answer;
			for(int gameNum =1; gameNum <= num; gameNum++){
				s = b.readLine();
				sA = s.substring(0,s.indexOf(' '));
				sB = s.substring(s.indexOf(' ')+1,s.length());

				int cnt = 0;
				
				for(int j = 0;j < fairList.size();j++){
					if(cmp(sA, fairList.get(j))>=0 && cmp(sB,fairList.get(j))<=0){
						cnt++;
					}
				}
				
				
				answer = "Case #"+gameNum+": "+cnt;
				
				System.out.println(answer+" time:"+((float)(System.currentTimeMillis()-start))/1000f+"s");
				fw.write(answer+"\r\n");
				
			}
			
			fw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int count(String s){
		int c = 0;
		int d = 0;
		for(int i = 0;i<s.length();i++){
			d += Integer.valueOf(String.valueOf(s.charAt(i)));
			if(d>=10){
				return -1;
			}
			if(s.charAt(i)=='2'){
				c++;
				if(c>3){
					return -1;
				}
			}
		}
		return 1;
	}
	
	public static void generateKaibun(String s, String limit){
		String s0,s1,s2;
		s0 = '0'+s+'0';
		s1 = '1'+s+'1';
		s2 = '2'+s+'2';
		
		String ss0,ss1,ss2;
		ss0 = square(s0);
		ss1 = square(s1);
		ss2 = square(s2);
		
		if(cmp(s,limit)<0){
			return;
		}
		
		if(cmp(ss1,limit)>=0&&checkKaibun(ss1)){
			fairList.add(ss1);
		}
		if(cmp(ss2,limit)>=0&&checkKaibun(ss2)){
			fairList.add(ss2);
		}

		if(cmp(ss0,limit)>0 && count(s0)>=0){
			generateKaibun(s0, limit);
		}

		if(cmp(ss1,limit)>0 && count(s1)>=0){
			generateKaibun(s1, limit);
		}

		if(cmp(ss2,limit)>0 && count(s2)>=0){
			generateKaibun(s2, limit);
		}

	}

	public static int cmp(String a, String b){
		
		if(a.length() > b.length()){
			return -1;
		}else if(a.length() < b.length()){
			return 1;
		}else{
			for(int i = 0; i < a.length();i++){
				if(Integer.valueOf(a.charAt(i))>Integer.valueOf(b.charAt(i))){
					return -1; 
				}else if(Integer.valueOf(a.charAt(i))<Integer.valueOf(b.charAt(i))){
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	public static boolean checkKaibun(String s){
		
		for(int i = 0; i < s.length()/2;i++){
			if(s.charAt(i) != s.charAt(s.length()-1 -i)){
				return false;
			}
		}
		
		return true;
	}
	
	public static String square(String s){
		BigInteger x = new BigInteger(s);
		if(x.equals("0")){
			return s;
		}
		return x.pow(2).toString();
	}

}
