package org.ming.leetcodeoj.thought.dp;

/**
 * 最小编辑距离
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class MinDistance {

    public static void main(String[] args) {
        /*
        Bearer  eyJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjEsInBob25lIjoiMTc2MTIyMDU0OTEiLCJsb2dpblR5cGUiOjUsInVzZXJObyI6IjEiLCJyb2xlcyI6W10sImxvZ2luSXAiOiIxNzUuOS4xNDAuMTEyIiwibmlja25hbWUiOiLmnY7mmI4iLCJkZXB0SWQiOjksImxvZ2luRnJvbSI6bnVsbCwiZXhwIjoxNjY2NzcwMDA4LCJ1c2VybmFtZSI6IuadjuaYjiJ9.Hbl6AgiYLxnUgXKZtCFwjNBqxb5CvqwoFCjdzu3hxpMLfDOGIZbdQslkxt9R8G3XhsF3KDrD6Rm4N9LE0sFb6pyJgRdBoO4Bcok7czv16hMTQmEWtUNBA08HF40kTooBQaUvFo35ua5tIgLru2btAlP56eBYXOfsxI-oc6GyKo4
         */
        String word1 = "horse", word2 = "ros";
        System.out.println(minDistance(word1, word2));
        word1 = "intention";
        word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }

    /*
    https://alchemist-al.com/ 表格演示
    对于两个字符串的 DP 问题，一般用一个二维数组进行状态表示

     */

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 使用len+1，目的是初始化空串的时候的情况
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 当word1为空串，如何构成word2，新增操作
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        // 当 word2为空串，word1如何构造word2，删除操作
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 删除操作： dp[i-1][j]
                // 增加操作: dp[i][j-1]
                // 替换操作: dp[i-1][j-1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }


}
