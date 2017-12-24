import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private int TableSize;
    private int cellSize;
    private boolean[][] LifeTable;//true表示该细胞为活细胞，false表示该细胞为死细胞
    private int[][] NeighborNum;
    private JPanel panel;
    public GameOfLife(boolean[][] LifeTable,int TableSize){
        initComponents();
        setTitle("生命游戏");
        this.LifeTable=LifeTable;
        this.TableSize=TableSize;
        cellSize=500/TableSize;
        NeighborNum=new int[TableSize][TableSize];
        repaint();
    }

    /*
    判断某个位置是不是活细胞
    1.这个位置必须是在边界里面
    2.这个位置的布尔值为true
     */
    public int ifLiveCell(int x,int y){
        if(x>=0&&x<TableSize&&y>=0&&y<TableSize&&LifeTable[x][y]==true){
            return 1;
        }else{
            return 0;
        }
    }

    /*
    获得单个细胞周围的8个相邻位置的活细胞数
     */
    public int getNeighborNum(int x,int y){
        int NeighborNum=0;
        NeighborNum+=ifLiveCell(x+1,y+1);
        NeighborNum+=ifLiveCell(x+1,y);
        NeighborNum+=ifLiveCell(x+1,y-1);
        NeighborNum+=ifLiveCell(x,y+1);
        NeighborNum+=ifLiveCell(x,y-1);
        NeighborNum+=ifLiveCell(x-1,y-1);
        NeighborNum+=ifLiveCell(x-1,y);
        NeighborNum+=ifLiveCell(x-1,y+1);

        return NeighborNum;
    }


    /*
    获取每个细胞周围的活细胞数
     */
    public void setNeighborNum(){
        for(int i=0;i<TableSize;i++){
            for(int j=0;j<TableSize;j++){
                NeighborNum[i][j]=getNeighborNum(i,j);
            }
        }
    }

    /*
    更新细胞生存状态
    1.   当前细胞为存活状态时，当周围低于2个（不包含2个）存活细胞时，该细胞变成死亡状态。（模拟生命数量稀少）
    2.   当前细胞为存活状态时，当周围有2个或3个存活细胞时，该细胞保持原样。
    3.   当前细胞为存活状态时，当周围有3个以上的存活细胞时，该细胞变成死亡状态。（模拟生命数量过多）
    4.   当前细胞为死亡状态时，当周围有3个存活细胞时，该细胞变成存活状态。（模拟繁殖）
     */
    public void GenerateLife(){
        for(int i=0;i<TableSize;i++){
            for(int j=0;j<TableSize;j++){
                if(LifeTable[i][j]==true){
                    if(NeighborNum[i][j]!=2&&NeighborNum[i][j]!=3){
                        LifeTable[i][j]=false;
                    }
                }else{
                    if(NeighborNum[i][j]==3){
                        LifeTable[i][j]=true;
                    }
                }
            }
        }
    }

    /*
    在控制台打印出每个细胞的状态，“-”表示细胞死亡，“*”表示细胞存活
     */
    public void display(){
        for(int i=0;i<TableSize;i++){
            for(int j=0;j<TableSize;j++){
                if(LifeTable[i][j]==true){
                    System.out.print("*");
                }else{
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }

    /*
    初始化构造图形界面
     */
    public void initComponents(){
        panel=new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setMinimumSize(new java.awt.Dimension(525, 550));
        panel.setPreferredSize(new java.awt.Dimension(525, 550));
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        pack();
    }

    /*
    将每次新一代细胞产生后画出来
     */
    public void paint(Graphics graph){
        super.paint(graph);
        for(int i=0;i<TableSize;i++){
            for(int j=0;j<TableSize;j++){
                if(LifeTable[i][j]==true){
                    graph.setColor(Color.BLACK);//如果细胞为活的，那么细胞颜色为黑色
                }else{
                    graph.setColor(Color.LIGHT_GRAY);//如果细胞为死的，那么细胞颜色为灰色
                }
                graph.fillRect(25+i * cellSize, 50+j * cellSize, cellSize - 1, cellSize - 1);
            }

        }
    }

    /*
    主函数入口
     */
    public static void main(String args[]){

        Thread thread = new Thread() {
            public void run() {
                /*
                初始化种子，TableSize表示一行有多少个细胞格子
                 */
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
                Gol.setVisible(true);
                while (true){
                    try {
                        /*
                        为了让每次细胞变化能够清楚地看到，我特意让线程在每次变化后休眠1秒
                         */
                        Thread.sleep(1000);
                        Gol.repaint();
                        Gol.display();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Gol.setNeighborNum();
                    Gol.GenerateLife();
                }
            }
        };
        thread.run();
    }
}
