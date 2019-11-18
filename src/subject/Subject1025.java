package subject;

public class Subject1025 {
    static class Solution {
        public boolean divisorGame(int N) {
            return search(N, 0);
        }

        boolean search(int N, int count) {
            for (int i = 2; i < N; i++) {
                if (N % i == 0) {
                    if( isPrim(N - i) && count%2==1)
                        return true;
                    else{
                        if (search(N-i,count+1)){
                            return true;
                        }
                    }
                }
            }
            return count % 2 == 1;
        }

        boolean isPrim(int n) {
            for (int i = 1; i < Math.sqrt(n); i++) {
                if (n % i == 1)
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divisorGame(2));
    }
}
