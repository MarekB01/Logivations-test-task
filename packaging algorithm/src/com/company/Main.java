package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner loInput = new Scanner(System.in);


        int liCntWidth = loInput.nextInt();
        int liCntLength = loInput.nextInt();
        int liCntHeight = loInput.nextInt();


        Shape loContainer = new Box(liCntWidth, liCntLength, liCntHeight);


        int liBoxCount = loInput.nextInt();


        List<Shape> loBoxes = new ArrayList<Shape>();
        while (liBoxCount-- > 0) {
            int liBoxWidth = loInput.nextInt();
            int liBoxLength = loInput.nextInt();
            int liBoxHeight = loInput.nextInt();
            loBoxes.add(new Box(liBoxWidth, liBoxLength, liBoxHeight));
        }

        
        PackagingAlgorithm loAlg = new PackagingAlgorithm();
        loAlg.statPack(loContainer, loBoxes);

    }
}
