package org.example;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void whenOneIsAdded_thenSizeEquals1Classic() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App(PersistanceOption.CLASSIC_COLLECTION).loadData();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }

    @Test
    public void whenOneIsAdded_thenSizeEquals1Optimized() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }

    @Test
    public void whenOneIsAdded_thenSizeEquals1DatabaseDisk() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App(PersistanceOption.DATABASE_DISK).loadData();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }

    @Test
    public void whenOneIsAdded_thenSizeEquals1DatabaseMemory() {
        Row added = new Row(999999, 1, "", "", "");
        RowContainer containerToTest = new App(PersistanceOption.DATABASE_MEMORY).loadData();

        final long expectedSize = 60001;

        containerToTest.add(added);
        assertEquals(expectedSize, containerToTest.size());
    }


    @Test
    public void whenCheckingById_getCorrectRow() {
        RowContainer containerToTest = new App(PersistanceOption.CLASSIC_COLLECTION).loadData();

        final Row rowToExpect = new Row(99991, 1, "", "", "");
        containerToTest.add((new Row(99992, 1, "", "", "")));
        containerToTest.add((new Row(99993, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(99991);

        assertEquals(obtainedRow, rowToExpect);
    }

    @Test
    public void whenCheckingById_getCorrectRowOptimized() {
        RowContainer containerToTest = new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData();

        final Row rowToExpect = new Row(99991, 1, "", "", "");
        containerToTest.add((new Row(99992, 1, "", "", "")));
        containerToTest.add((new Row(99993, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(99991);

        assertEquals(obtainedRow, rowToExpect);
    }

    @Test
    public void whenCheckingById_getCorrectRowDatabaseDisk() {
        RowContainer containerToTest = new App(PersistanceOption.DATABASE_DISK).loadData();

        final Row rowToExpect = new Row(99991, 1, "", "", "");
        containerToTest.add((new Row(99992, 1, "", "", "")));
        containerToTest.add((new Row(99993, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(99991);

        assertEquals(obtainedRow, rowToExpect);
    }

    @Test
    public void whenCheckingById_getCorrectRowDatabaseMemory() {
        RowContainer containerToTest = new App(PersistanceOption.DATABASE_MEMORY).loadData();

        final Row rowToExpect = new Row(99991, 1, "", "", "");
        containerToTest.add((new Row(99992, 1, "", "", "")));
        containerToTest.add((new Row(99993, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(99991);

        assertEquals(obtainedRow, rowToExpect);
    }


    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_Classic() {
        RowContainer wholeContainer = new App(PersistanceOption.CLASSIC_COLLECTION).loadData();

        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(99992, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(99991, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(99993, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(99992L));
        assertFalse(found.contains(99993L));
    }

    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_Optimized() {
        RowContainer wholeContainer = new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData();

        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(99992, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(99991, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(99993, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(99992L));
        assertFalse(found.contains(99993L));
    }

    @Ignore
    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_DatabaseDisk() {
        RowContainer wholeContainer = new App(PersistanceOption.DATABASE_DISK).loadData();

        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(99992, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(99991, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(99993, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(99992L));
        assertFalse(found.contains(99993L));
    }

    @Ignore
    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_DatabaseMemory() {
        RowContainer wholeContainer = new App(PersistanceOption.DATABASE_MEMORY).loadData();

        final int TOPIC_MOVIE = 0;
        final int TOPIC_NATURE = 1;
        wholeContainer.add((new Row(99992, TOPIC_MOVIE, "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(99991, TOPIC_NATURE, "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(99993, TOPIC_MOVIE, "Worst!?", "What's the worst series ever produced?", "Teletubbies")));

        Collection<Long> found = wholeContainer.findIdsByKeyword("movie");

        assertTrue(found.size() >= 2);
        assertTrue(found.contains(99992L));
        assertFalse(found.contains(99993L));
    }


    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_Classic() {
        RowContainer wholeContainer = new App(PersistanceOption.CLASSIC_COLLECTION).loadData();

        wholeContainer.add(new Row(99991, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(99992, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }

    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_Optimized() {
        RowContainer wholeContainer = new App(PersistanceOption.OPTIMIZED_COLLECTION).loadData();

        wholeContainer.add(new Row(99991, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(99992, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }

    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_DatabaseDisk() {
        RowContainer wholeContainer = new App(PersistanceOption.DATABASE_DISK).loadData();

        wholeContainer.add(new Row(99991, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(99992, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }

    @Test
    public void whenSearchForExistingTitleInCollection_thenSuccess_DatabaseMemory() {
        RowContainer wholeContainer = new App(PersistanceOption.DATABASE_MEMORY).loadData();

        wholeContainer.add(new Row(99991, 1, "myTitle", "", ""));
        wholeContainer.add(new Row(99992, 1, "myOtherTitle", "", ""));
        Collection<Row> found = wholeContainer.getByTitle("myTitle");
        assertTrue(found.size() == 1);
    }
}
