import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(0,Gol.ifLiveCell(-2,-2));
        assertEquals(1,Gol.ifLiveCell(5,7));
    }

    @Test
    public void getNeighborNum() {
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
        assertEquals(2,Gol.getNeighborNum(5,5));
        assertEquals(1,Gol.getNeighborNum(5,4));
    }

    @Test
    public void setNeighborNum() {
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

    }

    @Test
    public void generateLife() {

    }
}