package com.company;

import java.util.*;

public class PackagingAlgorithm {

    private static boolean ibShowProgress = false;

    @SuppressWarnings("unchecked")
    public void statPack(Shape aContainer, List<Shape> aShapes) {
        System.out.println("Starting to pack.");


        ArrayList loShapesCopy = new ArrayList<Shape>(Arrays.asList(aShapes
                .toArray(new Shape[0])));


        long llStartTime = System.currentTimeMillis();


        int liNumContainers = this.pack(aContainer, loShapesCopy);


        long llEndTime = System.currentTimeMillis();


        long llElapsedTime = llEndTime - llStartTime;
        int liNumBoxesPacked = (aShapes.size() - loShapesCopy.size());
        int liTotalBoxVolume = Shape.getTotalVolume(aShapes);
        int liTotalContainerVolume = (liNumContainers * aContainer.getVolume());
        float lfPackingEfficiency = ((float) liTotalBoxVolume / liTotalContainerVolume);


        System.out.println("Complete.");
        System.out.println("Packed " + liNumBoxesPacked + " of " + aShapes.size()
                + " boxes into " + liNumContainers + " container(s) of "
                + aContainer);
        System.out.println("Statistics:");
        System.out.println("Time elapsed: \t\t" + llElapsedTime
                + " milliseconds");
        System.out.println("Average: \t\t"
                + ((float) liNumBoxesPacked / liNumContainers)
                + " boxes/container");
        System.out.println("Packing efficiency: \t"
                + (lfPackingEfficiency * 100) + "%");
    }


    public int pack(Shape aContainer, List<Shape> aShapes) {

        Collections.sort(aShapes, new shapeAreaComparator());
        Collections.reverse(aShapes);
        return this.pack(aContainer, aShapes, 0, false);
    }


    private int pack(Shape aContainer, List<Shape> aShapes,
                     int aiContainerNum, boolean abSubContainer) {


        Iterator<Shape> loIterator = aShapes.iterator();
        Shape loCurrentShape = null;
        boolean lbFoundFit = false;
        while (!lbFoundFit && loIterator.hasNext()) {
            loCurrentShape = loIterator.next();

            if (aContainer.attemptToContain(loCurrentShape))
                lbFoundFit = true;
        }

        if (lbFoundFit) {

            loCurrentShape.matchPositionOf(aContainer);
            aShapes.remove(loCurrentShape);

            if (ibShowProgress)
                System.out.println("Packed " + loCurrentShape.toFullString()
                        + " into container " + aiContainerNum + ".");


            List<Shape> loSubContainers = loCurrentShape.breakUp(aContainer);
            for (Shape loSubContainer : loSubContainers) {
                this.pack(loSubContainer, aShapes, aiContainerNum, true);
            }
        }


        if (abSubContainer == false && aShapes.size() > 0) {
            return this.pack(aContainer, aShapes, aiContainerNum + 1, false);
        } else {

            return aiContainerNum + 1;
        }
    }
}



