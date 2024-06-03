package com.example.shopserve.result;


import com.example.shopserve.entity.Recommend;

import java.util.ArrayList; 7-
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimilarityUtil {

    public static List<String> getSimilarity(List<Recommend> userList,  Map<Integer, Map<Integer, Integer>> userRatings) {
        List<String> resultList = new ArrayList<String>();
        //循环每一个对象画像
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : userRatings.entrySet()) {
            double molecular = 0.00;
            double denominator1 = 0.00;
            double denominator2 = 0.00;
            Map<Integer, Integer> innerMap = entry.getValue();
            //比较本用户和对象用户是否对同一个电影评价过，得到公式的因子
            for (Recommend r1 : userList) {
                for (Map.Entry<Integer, Integer> r2 : innerMap.entrySet()) {
                    if (r1.getMovie_id().equals(r2.getKey())) {
                        molecular += r1.getScore() * r2.getValue();
                        denominator1 += r1.getScore() * r1.getScore();
                        denominator2 += r2.getValue() * r2.getValue();
                    } }
            }//进行运算
            if (denominator1 != 0 && denominator2 != 0) {
                denominator1 = Math.sqrt(denominator1);
                denominator2 = Math.sqrt(denominator2);
                double cos = molecular / (denominator1 * denominator2);
                String result = userList.get(0).getUser_id() + "," + entry.getKey() + "," + cos;
                resultList.add(result);
            } else {
                String result = userList.get(0).getUser_id() + "," +entry.getKey() + "," + 0.2;
                resultList.add(result); }
        }// 输出格式userId,userId,score
        return resultList;
    }
}
