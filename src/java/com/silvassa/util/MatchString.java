/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.util;

/**
 *
 * @author CEINFO
 */
public class MatchString {
    public static int getMatchPercentage(String s0, String s1) {
        int percntg = 0;

        if (s0 == null) {
            s0 = "";
        }
        if (s1 == null) {
            s1 = "";
        }
        int length = s0.length();
        if (length == 0) {
            length = 1;
        }
        int diff = LevenshteinDistance(s0, s1);
        //System.out.println("diff : " + diff);
        //System.out.println("length : " + length);
        if (diff > 0) {
            percntg = ((diff * 100) / length);
        }
        //System.out.println("percntg : " + percntg);
        return 100 - percntg;
    }

    private static int LevenshteinDistance(String s0, String s1) {

        int len0 = s0.length() + 1;
        int len1 = s1.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) {
            cost[i] = i;
        }

        // dynamicaly computing the array of distances
        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {

            // initial cost of skipping prefix in String s1
            newcost[0] = j - 1;

            // transformation cost for each letter in s0
            for (int i = 1; i < len0; i++) {

                // matching current letters in both strings
                int match = (s0.charAt(i - 1) == s1.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert = cost[i] + 1;
                int cost_delete = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete),
                        cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

}
