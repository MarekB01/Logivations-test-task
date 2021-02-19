package com.company;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PackingAlgorithmTest extends TestCase {

    private PackagingAlgorithm ioAlg;
    private Shape ioContainer;
    private Random ioGenerator;

    @Override
    protected void setUp() {
        ioAlg = new PackagingAlgorithm();
        ioContainer = new Box(3, 4, 5);
        ioGenerator = new Random();
    }


    public void testPack() {
        List<Shape> loShapes = new ArrayList<Shape>();
        loShapes.add(new Box(1, 3, 1));
        loShapes.add(new Box(3, 1, 1));
        loShapes.add(new Box(2, 2, 4));
        loShapes.add(new Box(3, 2, 5));
        loShapes.add(new Box(1, 1, 1));
        Assert.assertTrue(ioAlg.pack(ioContainer, loShapes) > 0);
        Assert.assertEquals(0, loShapes.size()); // 0 left unpacked
    }


    public void testPackFull() {
        List<Shape> loShapes = new ArrayList<Shape>();
        loShapes.add(new Box(3, 4, 2));
        loShapes.add(new Box(3, 2, 3));
        loShapes.add(new Box(3, 2, 3));
        Assert.assertTrue(ioAlg.pack(ioContainer, loShapes) == 1);
        Assert.assertEquals(0, loShapes.size()); // 0 left unpacked
    }

    public void testPackAdvanced() {
        int liContainerWidth = 10;
        int liContainerLength = 10;
        int liContainerHeight = 10;
        Box loContainer = new Box(liContainerWidth, liContainerLength, liContainerHeight);
        List<Shape> loShapes = new ArrayList<Shape>();
        int liNumBoxes = 20000;
        while (--liNumBoxes >= 0) {
            ((ArrayList) loShapes).add(new Box(
                    1 + (int) (Math.round(ioGenerator.nextDouble() * (liContainerWidth - 1))),
                    1 + (int) (Math.round(ioGenerator.nextDouble() * (liContainerLength - 1))),
                    1 + (int) (Math.round(ioGenerator.nextDouble() * (liContainerHeight - 1)))
            ));
        }
        Assert.assertTrue(ioAlg.pack(loContainer, loShapes) > 0);
        Assert.assertEquals(0, loShapes.size());
    }
}

