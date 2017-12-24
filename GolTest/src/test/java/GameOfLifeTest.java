import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeTest {


    @Test
    public void ifLiveCell() {
        int TableSize=10;
        boolean[][] LifeTable=new boolean[TableSize][TableSize];
        for(int i=0;i<TableSize;i++){
            for(int j=0;j<TableSize;j++){
                LifeTable[i][j]=false;
            }
        }
        LifeTable[5][4]=true;
        LifeTable[5][5]=true;
        LifeTable[5][6]=true;
        LifeTable[5][7]=true;
        GameOfLife Gol=new GameOfLife(LifeTable,TableSize);
        assertEquals(1,Gol.ifLiveCell(-2,-2));
    }

    @Test
    public void getNeighborNum() {

    }

    @Test
    public void setNeighborNum() {

    }

    @Test
    public void generateLife() {

    }
}