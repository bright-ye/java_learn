package com.mylearn.nio;

import java.util.HashMap;
import java.util.Map;

public class Main {
    /**
     * @param serverNum   服务器个数
     * @param taskTypeNum 任务类型个数
     * @param task        任何数组
     * @return
     */
    public int weight(int serverNum, int taskTypeNum, int[] task) {
        // 服务器中任务个数最多的服务器编号
        int sIndex = Integer.MIN_VALUE;
        // 任务个数最多服务器中的任务个数
        int max = Integer.MIN_VALUE;
        // key:server编号;value:任务个数
        Map<Integer, Integer> map = new HashMap<>();
        // 先把任务都放入服务器中
        for (int i = 0; i < taskTypeNum; i++) {
            if (task[i] > max) {
                sIndex = i;
                max = task[i];
            }
            map.put(i, task[i]);
        }

        // 当有服务器为空的时候，就从服务器中任务个数最多的服务器中取出一半任务放入到空闲服务器中
        while (map.size() < serverNum) {
            map.put(map.size(), max / 2);
            map.put(sIndex, max - max / 2);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    sIndex = entry.getKey();
                }
            }
        }
        return max;
    }
}

