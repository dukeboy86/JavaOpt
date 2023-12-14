package org.example;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void whenOneIsAdded_thenSizeEquals1Classic() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App().loadDataClassic();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }

    @Test
    public void whenOneIsAdded_thenSizeEquals1Optimized() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App().loadDataOptimized();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }

    @Test
    public void whenCheckingById_getCorrectRow() {
        RowContainer containerToTest = new App().loadDataClassic();
        final Row rowToExpect = new Row(1, 1, "", "", "");
        containerToTest.add((new Row(1, 1, "", "", "")));
        containerToTest.add((new Row(3, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(1);

        assertEquals(obtainedRow, rowToExpect);
    }

    @Test
    public void whenCheckingById_getCorrectRowOptimized() {
        RowContainer containerToTest = new App().loadDataOptimized();
        final Row rowToExpect = new Row(1, 1, "", "", "");
        containerToTest.add((new Row(1, 1, "", "", "")));
        containerToTest.add((new Row(3, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(1);
        assertEquals(obtainedRow, rowToExpect);
    }

    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_Classic() {

        RowContainer wholeContainer = new App().loadDataClassic();
        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(2, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(1, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(3, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

//        assertTrue(found.size() == 2 && found.contains(3));

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(2L));
        assertFalse(found.contains(3L));
    }

    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_Optimized() {

        RowContainer wholeContainer = new App().loadDataOptimized();
        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(2, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(1, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(3, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

//        assertTrue(found.size() == 2 && found.contains(3));

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(2L));
        assertFalse(found.contains(3L));
    }


    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_Classic() {
        RowContainer wholeContainer = new App().loadDataClassic();
        wholeContainer.add(new Row(1, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(2, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }

    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_Optimized() {
        RowContainer wholeContainer = new App().loadDataOptimized();
        wholeContainer.add(new Row(1, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(2, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }

}
