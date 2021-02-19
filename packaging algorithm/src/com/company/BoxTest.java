package com.company;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class BoxTest extends TestCase {

        private Shape ioBox1W3L3H;
        private Shape ioBox2W3L4H;
        private Shape ioBox3W3L3H;
        private Shape ioBox3W4L5H;
        private List<Shape> ioBoxes;

        @Override
        protected void setUp() {
            ioBox1W3L3H = new Box(1, 3, 3);
            ioBox2W3L4H = new Box(2, 3, 4);
            ioBox3W4L5H = new Box(3, 4, 5);
            ioBox3W3L3H = new Box(3, 3, 3);
            ioBoxes = new ArrayList<Shape>();
            ioBoxes.add(ioBox1W3L3H);
            ioBoxes.add(ioBox2W3L4H);
            ioBoxes.add(ioBox3W4L5H);
            ioBoxes.add(ioBox3W3L3H);
        }

        public void testBreakUp() {
            List<Shape> loSubShapes = ioBox1W3L3H.breakUp(ioBox3W4L5H);
            Assert.assertEquals(3, loSubShapes.size());
            Shape loSubShapeX = loSubShapes.get(0);
            Shape loSubShapeY = loSubShapes.get(1);
            Shape loSubShapeZ = loSubShapes.get(2);
            Assert.assertEquals(new Box(2, 4, 5, 1, 0, 0), loSubShapeX);
            Assert.assertEquals(new Box(1, 1, 5, 0, 3, 0), loSubShapeY);
            Assert.assertEquals(new Box(1, 3, 2, 0, 0, 3), loSubShapeZ);
        }


        public void testCanContain() {
            Assert.assertTrue(ioBox2W3L4H.canContain(ioBox1W3L3H));
            Assert.assertTrue(ioBox3W4L5H.canContain(ioBox2W3L4H));
            Assert.assertFalse(ioBox2W3L4H.canContain(ioBox3W4L5H));
            Assert.assertFalse(ioBox2W3L4H.canContain(ioBox3W4L5H));
        }


        public void testAttemptToContain() {
            Assert.assertTrue(ioBox3W4L5H.attemptToContain(ioBox3W3L3H));
            Assert.assertTrue(ioBox3W3L3H.attemptToContain(ioBox1W3L3H));
            Assert.assertFalse(ioBox3W3L3H.attemptToContain(ioBox3W4L5H));
            Assert.assertFalse(ioBox2W3L4H.attemptToContain(ioBox3W4L5H));
        }


        public void testGetArea() {
            Assert.assertEquals(3, ioBox1W3L3H.getArea());
            Assert.assertEquals(6, ioBox2W3L4H.getArea());
            Assert.assertEquals(12, ioBox3W4L5H.getArea());
            Assert.assertEquals(9, ioBox3W3L3H.getArea());
        }


        public void testGetTotalVolume() {
            Assert.assertEquals(120, Shape.getTotalVolume(ioBoxes));
        }


        public void testGetVolume() {
            Assert.assertEquals(9, ioBox1W3L3H.getVolume());
            Assert.assertEquals(24, ioBox2W3L4H.getVolume());
            Assert.assertEquals(60, ioBox3W4L5H.getVolume());
            Assert.assertEquals(27, ioBox3W3L3H.getVolume());
        }

        public void testRotateXY() {
            ioBox3W4L5H.rotateXY();
            Assert.assertEquals(4, ((Box) ioBox3W4L5H).iWidth);
            Assert.assertEquals(3, ((Box) ioBox3W4L5H).iLength);
            Assert.assertEquals(5, ((Box) ioBox3W4L5H).iHeight);
            ioBox3W4L5H.rotateXY();
            Assert.assertEquals(3, ((Box) ioBox3W4L5H).iWidth);
            Assert.assertEquals(4, ((Box) ioBox3W4L5H).iLength);
            Assert.assertEquals(5, ((Box) ioBox3W4L5H).iHeight);
        }


        public void testShapeAreaComparator() {
            Collections.sort(ioBoxes, new shapeAreaComparator());
            Assert.assertEquals(3, ioBoxes.get(0).getArea());
            Assert.assertEquals(6, ioBoxes.get(1).getArea());
            Assert.assertEquals(9, ioBoxes.get(2).getArea());
            Assert.assertEquals(12, ioBoxes.get(3).getArea());
        }


        public void testShapeVolumeComparator() {
            Collections.sort(ioBoxes, new shapeVolumeComparator());
            Assert.assertEquals(9, ioBoxes.get(0).getVolume());
            Assert.assertEquals(24, ioBoxes.get(1).getVolume());
            Assert.assertEquals(27, ioBoxes.get(2).getVolume());
            Assert.assertEquals(60, ioBoxes.get(3).getVolume());
        }

    }
