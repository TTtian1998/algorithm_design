package DynamicProgram.Num3;

/**
 * @author 田泽鑫
 * @date 2019/12/25
 * @description
 */
public class LongestPalindromicSubsequenceDemo {

    private static void longestPalindromicSubsequence(String str) {
        int len = str.length();
        int [][] lps = new int[len][len];

        for(int i=0; i<len; i++) {
            for(int j=0; j<i; j++) {
                lps[i][j] = 0;
            }
        }

        for(int i=0; i<len; i++) {
            lps[i][i] = 1;
        }

        for(int step=1; step<=len-1; step++) {
            for(int i=0; i<len-step; i++) {
                if(str.charAt(i) == str.charAt(i+step)) {
                    lps[i][i+step] = lps[i+1][i+step-1]+2;
                } else {
                    lps[i][i+step] = max(lps[i+1][i+step], lps[i][i+step-1]);
                }
            }
        }

        /*for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                System.out.print(lps[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println("LPS length for " + str + " is " + lps[0][len-1]);
    }

    private static int max(int i, int j) {
        return i>j ? i : j;
    }

    public static void main(String[] args) {
        longestPalindromicSubsequence("BBABCBCAB");
        longestPalindromicSubsequence("ABHISHEK");
        longestPalindromicSubsequence("ABADCDABAKK");
    }

}