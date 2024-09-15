package com.tuxt.mytest.algorithm;

import java.util.*;

/**
 * 一只12升的水杯盛满了水，另有两只分别是9升和5升的空水杯，怎样利用这两个空杯把杯子里的12升水平均分为两份？
 */
public class WaterPouring {
    private static final int CAP12 = 12;
    private static final int CAP9 = 9;
    private static final int CAP5 = 5;
    // 初始状态，12升水杯满，9升和5升水杯为空
    static int[] startState = {CAP12, 0, 0};
    public static void main(String[] args) {

        Map<String, int[]> visited = new HashMap<>();
        Map<String, String> actionMap = new HashMap<>();
        List<int[]> solution = bfs(startState, visited, actionMap);
        if (solution != null) {
            printSolution(solution);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static List<int[]> bfs(int[] initialState, Map<String, int[]> visited, Map<String, String> actionMap) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(initialState);
        visited.put(key(initialState), initialState);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == 6 && current[1] == 6) {
                return constructSolution(current, actionMap);
            }
            List<int[]> possibleMoves = getPossibleMoves(current);
            for (int[] state : possibleMoves) {
                if (!visited.containsKey(key(state))) {
                    visited.put(key(state), state.clone());
                    actionMap.put(key(state), key(current));
                    queue.offer(state.clone());
                }
            }
        }
        return null;
    }

    private static List<int[]> constructSolution(int[] finalState, Map<String, String> actionMap) {
        List<int[]> solution = new ArrayList<>();
        String stateKey = key(finalState);
        solution.add(finalState);
        String key = key(startState);
        while (!stateKey.equals(key)) {
            if (actionMap.containsKey(stateKey)) {
                solution.add(strToIntArray(actionMap.get(stateKey)));
                stateKey=actionMap.get(stateKey);
            }
        }
        Collections.reverse(solution);

        return solution;
    }

    public static int[] strToIntArray(String str) {
        // 移除字符串中的 "[" 和 "]"
        str = str.substring(1, str.length() - 1);
        // 分割字符串
        String[] parts = str.split(",");
        // 创建一个整型数组
        int[] array = new int[parts.length];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i].trim());
        }
        return array;
    }

    private static String key(int[] state) {
        return Arrays.toString(state);
    }

    private static void printSolution(List<int[]> solution) {
        for (int[] state : solution) {
            System.out.println("12升杯: " + state[0] + "升, 9升杯: " + state[1] + "升, 5升杯: " + state[2] + "升");
        }
    }

    private static int[][] moves = {
        {0, 1}, // 12升杯倒9升杯
        {0, 2}, // 12升杯倒5升杯
        {1, 0}, // 9升杯倒12升杯
        {1, 2}, // 9升杯倒5升杯
        {2, 0}, // 5升杯倒12升杯
        {2, 1}  // 5升杯倒9升杯
    };

    private static int[] getNewState(int[] state, int[] move) {
        int[] newState = state.clone();
        int pourAmount = Math.min(state[move[0]], getStateCapacity(move[1]) - state[move[1]]);
        newState[move[0]] -= pourAmount;
        newState[move[1]] += pourAmount;
        return newState;
    }

    private static int getStateCapacity(int stateIndex) {
        switch (stateIndex) {
            case 0:
                return CAP12;
            case 1:
                return CAP9;
            case 2:
                return CAP5;
            default:
                return 0;
        }
    }

    private static List<int[]> getPossibleMoves(int[] state) {
        List<int[]> possibleMoves = new ArrayList<>();
        for (int[] move : moves) {
            int[] newState = getNewState(state, move);
            if (!key(state).equals(key(newState))) {
                possibleMoves.add(newState);
            }
        }
        return possibleMoves;
    }
}