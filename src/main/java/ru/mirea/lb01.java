package ru.mirea;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class lb01 {

    public static void main(String[] args) throws FileNotFoundException {
        int n=0;
        int v;
        int l=0;
        HashMap<String,Integer> hm= new HashMap<String,Integer>();
        try(Scanner s = new Scanner(new File("b.txt"), "Cp1251")){
            while(s.hasNext()){
                String word = s.next().toLowerCase();
                if(!hm.containsKey(word)){
                    hm.put(word,1);
                }else{
                    v= hm.get(word);
                    hm.put(word,++v);
                }
            }
            ArrayList<Map.Entry<String,Integer>> ar = new ArrayList<>(hm.entrySet());
            ar.sort((e1,e2)->-e1.getValue().compareTo(e2.getValue()));

            for (Map.Entry<String, Integer> e : ar) {
                System.out.println(e.getKey() + ":" + e.getValue());
            }

            DefaultCategoryDataset data = new DefaultCategoryDataset();
            String category = "Слова";
            for (Map.Entry<String, Integer> e : ar) {
                data.addValue(e.getValue(),category,e.getKey());
                System.out.println(e.getKey() + ":" + e.getValue());
                l++;
                if(l==10) break;
            }

            JFreeChart chart = ChartFactory.createBarChart("Слова", "Слово", "Встречается раз", data);
            BufferedImage image = chart.createBufferedImage(600, 400);
            File file = new File("chart.png");
            try {
                ImageIO.write(image, "png", file);
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

