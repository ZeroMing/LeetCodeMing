package org.ming.day1q1;

/**
 * @author liming53
 * @date 2023/4/11
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class _1041_M_IsRobotBounded {

    public static void main(String[] args) {
        // System.out.println(isRobotBounded("GGLLGG"));
        // System.out.println(isRobotBounded("GG"));
        System.out.println(isRobotBounded("GL"));
    }


    public static boolean isRobotBounded(String instructions) {
        char[] chars = instructions.toCharArray();
        int x = 0, y = 0, nextXStep = 0, nextYStep = 1;
        // 1->North;-1-South;
        // 2-East;-2-West;
        int face = 1;
        int i=0;
        for (;i < 4; i++) {
            System.out.println(i);
            for (char c : chars) {
                if (c == 'G') {
                    x = x + nextXStep;
                    y = y + nextYStep;
                } else if (c == 'L') {
                    if (face == 1) {
                        face = -2;
                        nextXStep = -1;
                        nextYStep = 0;
                    } else if (face == -2) {
                        face = -1;
                        nextYStep = -1;
                        nextXStep = 0;
                    } else if (face == -1) {
                        face = 2;
                        nextXStep = 1;
                        nextYStep = 0;
                    } else if (face == 2) {
                        face = 1;
                        nextYStep = 1;
                        nextXStep = 0;
                    }
                } else if (c == 'R') {
                    if (face == 1) {
                        face = 2;
                        nextXStep = 1;
                        nextYStep = 0;
                    } else if (face == 2) {
                        face = -1;
                        nextYStep = -1;
                        nextXStep = 0;
                    } else if (face == -1) {
                        face = -2;
                        nextXStep = -1;
                        nextYStep = 0;
                    } else if (face == -2) {
                        face = 1;
                        nextYStep = 1;
                        nextXStep = 0;
                    }
                }
            }
            if ((i == 0) || (i == 1) || (i == 3)) {
                if(x == 0 && y == 0){
                    return true;
                }
            }
        }

        return false;
    }

    /*
    1041. 困于环中的机器人
    中等
    218
    相关企业
    在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:
    北方向 是y轴的正方向。
    南方向 是y轴的负方向。
    东方向 是x轴的正方向。
    西方向 是x轴的负方向。
    机器人可以接受下列三条指令之一：
    "G"：直走 1 个单位
    "L"：左转 90 度
    "R"：右转 90 度
    机器人按顺序执行指令 instructions，并一直重复它们。
    只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。

    示例 1：
    输入：instructions = "GGLLGG"
    输出：true
    解释：
    机器人最初在(0,0)处，面向北方。
    “G”:移动一步。位置:(0,1)方向:北。
    “G”:移动一步。位置:(0,2).方向:北。
    “L”:逆时针旋转90度。位置:(0,2).方向:西。
    “L”:逆时针旋转90度。位置:(0,2)方向:南。
    “G”:移动一步。位置:(0,1)方向:南。
    “G”:移动一步。位置:(0,0)方向:南。
    重复指令，机器人进入循环:(0,0)——>(0,1)——>(0,2)——>(0,1)——>(0,0)。
    在此基础上，我们返回true。
    示例 2：

    输入：instructions = "GG"
    输出：false
    解释：机器人最初在(0,0)处，面向北方。
    “G”:移动一步。位置:(0,1)方向:北。
    “G”:移动一步。位置:(0,2).方向:北。
    重复这些指示，继续朝北前进，不会进入循环。
    在此基础上，返回false。


    示例 3：
    输入：instructions = "GL"
    输出：true
    解释：机器人最初在(0,0)处，面向北方。
    “G”:移动一步。位置:(0,1)方向:北。
    “L”:逆时针旋转90度。位置:(0,1).方向:西。
    “G”:移动一步。位置:(- 1,1)方向:西。
    “L”:逆时针旋转90度。位置:(- 1,1)方向:南。
    “G”:移动一步。位置:(- 1,0)方向:南。
    “L”:逆时针旋转90度。位置:(- 1,0)方向:东方。
    “G”:移动一步。位置:(0,0)方向:东方。
    “L”:逆时针旋转90度。位置:(0,0)方向:北。
    重复指令，机器人进入循环:(0,0)——>(0,1)——>(- 1,1)——>(- 1,0)——>(0,0)。
    在此基础上，我们返回true。


    提示：

    1 <= instructions.length <= 100
    instructions[i] 仅包含 'G', 'L', 'R'
     */
}
