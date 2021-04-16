import java.util.Scanner;

class WordWrap
{

	final int MAX = Integer.MAX_VALUE;
	private int m;
    WordWrap(int m){
        this.m = m;
    }

	int printSolution (int []p,String []words, int n)
	{
		int k;
		if (p[n-1] == 0)
		k = 0;
		else
		k = printSolution (p, words,p[n-1]) + 1;
        int start = p[n-1];
        int end = n-1;
        System.out.print("Line number" + k  +":");
        for(int i=start ; i<=end;i++){
            System.out.print(words[i]+ " ");
        }
        System.out.println("");

		return k;
	}

	void solveWordWrap (String line)
	{

        String []words = line.split(" ");
        int n = words.length;
        int []l = new int[n]; 
        for(int i=0;i<n;i++){
            l[i] = words[i].length();
        }
		int [][]extras = new int[n][n];
	

		int [][]lc= new int[n][n];
	

		int []c = new int[n];
	

		int []p =new int[n];
	
		for (int i = 0; i < n; i++){
			extras[i][i] = this.m - l[i];
			for (int j = i+1; j < n; j++)
			extras[i][j] = extras[i][j-1] - l[j] - 1;
		}


		for (int i = 0; i < n; i++){
			for (int j = i; j < n; j++){
				if (extras[i][j] < 0)
					lc[i][j] = MAX;
				else if (j == n-1 && extras[i][j] >= 0)
					lc[i][j] = 0;
				else
					lc[i][j] = extras[i][j]*extras[i][j]*extras[i][j];
			}
		}
		for (int j = 0; j < n; j++){
			c[j] = MAX;
			for (int i = 0; i <= j; i++){
                int prev;
                if(i==0){
                    prev = 0;
                }else{
                    prev = c[i-1];
                }
				if (prev != MAX && lc[i][j] != MAX &&
				(prev + lc[i][j] < c[j])){
					c[j] = prev + lc[i][j];
                    if(prev == 0 ){
                        p[j] = 0;
                    }else{
                        p[j] = i;
                    }
				}
			}
		}
        System.out.println("Cost:" + c[n-1]);
		printSolution(p, words, n);
	}
}

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter max line length");
        int m = scanner.nextInt();
        System.out.println("Enter a line with" + " " + "as seperator between words");
        scanner.nextLine();
        String line = scanner.nextLine();
        WordWrap w = new WordWrap(m);
        w.solveWordWrap (line);
        scanner.close();
    }
}
