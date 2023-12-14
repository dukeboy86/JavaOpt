package org.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void whenOneIsAdded_thenSizeEquals1(){
        Row added = new Row(1, 1, "", "", "");
        RowContainer containerToTest = new RowContainer();
        final int expectedSize = 1;
    
        containerToTest.add(added);
        AssertTrue(containerToTest.size().equals(expectedSize);
    }

    @Test 
    public void whenCheckingById_getCorrectRow() {
        RowContainer containerToTest = new RowContainer();
        final Row rowToExpect = new Row(1, 1, "", "", "");
        containerToTest.add((new Row(1, 1, "", "", "")));
        containerToTest.add((new Row(3, 3, "", "", "")));
        containerToTest.add(rowToExpect);
        final Row obtainedRow = containerToTest.getById(1);
        AssertTrue(obtainedRow.equals(rowToExpect));
    }
    @Test
    void whenAdding3TopicsAnd2OfThemAreSame_
    Collection<Row> getByTopic(long id);

    Collection<Row> getByQuestionTitle(String title);

    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch_withNormalCollection(){
        RowContainer wholeContainer = new RowContainer<>();
        wholeContainer.add((new Row(2, "film", "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(1, "nature", "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(3, "film", "Worst!?", "What's the worst series ever produced?", "Teletubbies")))));

        Collection<Long> found = wholeContainer.findIdsByKeyword("film");
        Assert.assertTrue(found.size().equals(2) && found.back().equals(3));
    }
    @Test
    public void whenSearchForKeywordInCollectionWithThemAtIndex0and2_thenFoundIndicesMatch(){

        RowContainer wholeContainer = new RowContainer();
        wholeContainer.add((new Row(2, "film", "Favorite Movie?", "What's your favorite movie?", "Home alone")));
        wholeContainer.add((new Row(1, "nature", "What trees eat animals?", "I have heard of some animals being a risk for children due to their behaviour. Which ones are there really?", "None")));
        wholeContainer.add((new Row(3, "film", "Worst!?", "What's the worst series ever produced?", "Teletubbies")))));

        Collection<Long> found = wholeContainer.findIdsByKeyword("film");
        Assert.assertTrue(found.size().equals(2) && found.back().equals(3));
    }
}
