package TheLongRoadHome.Handler;

import TheLongRoadHome.entity.Enemy;
import TheLongRoadHome.entity.Player;

import java.sql.*;
import java.util.Vector;

public class Database {
    private Connection connection = null;
    private Statement statement = null;
    private Vector<DatabaseElement> databaseElements;
    private int numberOfEntries;

    public Database (){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:DataBase");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable () throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS STUFF "  +
                    "(ID        INTEGER PRIMARY KEY NOT NULL, " +
                     "LEVEL INTEGER NOT NULL, " +
                     "SCORE INTEGER, " +
                     "NUMBER_ENEMY INTEGER, " +
                     "DIFFICULTY INTEGER, " +
                     "PLAYER_LIFE INTEGER, " +
                     "ENEMY1_LIFE INTEGER, " +
                     "ENEMY2_LIFE INTEGER, " +
                     "ENEMY3_LIFE INTEGER, " +
                     "ENEMY4_LIFE INTEGER, " +
                     "ENEMY5_LIFE INTEGER, " +
                     "ENEMY6_LIFE INTEGER, " +
                     "ENEMY7_LIFE INTEGER, " +
                     "PLAYER_X NUMERIC, " +
                     "PLAYER_Y NUMERIC, " +
                     "ENEMY1_X NUMERIC, " +
                     "ENEMY1_Y NUMERIC, " +
                     "ENEMY2_X NUMERIC, " +
                     "ENEMY2_Y NUMERIC, " +
                     "ENEMY3_X NUMERIC, " +
                     "ENEMY3_Y NUMERIC, " +
                     "ENEMY4_X NUMERIC, " +
                     "ENEMY4_Y NUMERIC, " +
                     "ENEMY5_X NUMERIC, " +
                     "ENEMY5_Y NUMERIC, " +
                     "ENEMY6_X NUMERIC, " +
                     "ENEMY6_Y NUMERIC, " +
                     "ENEMY7_X NUMERIC, " +
                     "ENEMY7_Y NUMERIC)";

        statement.executeUpdate(sql);
    }

    public void addElement (int ID, int LEVEL, int SCORE, int DIFFICULTY, Player player, Vector <Enemy> enemies) throws SQLException {
        int NUMBER_ENEMY = enemies.size();

        int PLAYER_LIFE = -1;
        if (NUMBER_ENEMY != 0){
            PLAYER_LIFE = player.getLife();
        }

        String enemyStr = "";

        enemyStr = enemyStr + ", " + PLAYER_LIFE;

        for (Enemy enemy : enemies){
            enemyStr = enemyStr + ", " + enemy.getLife();
        }

        for (int i = NUMBER_ENEMY; i < 7; i++){
            enemyStr = enemyStr + ", -1";
        }

        enemyStr = enemyStr + ", " + player.getPos().x + ", " + player.getPos().y;

        for (Enemy enemy : enemies){
            enemyStr = enemyStr + " , " + enemy.getPos().x + ", " + enemy.getPos().y;
        }

        for (int i = NUMBER_ENEMY; i < 7; i++){
            enemyStr = enemyStr + " , -1, -1";
        }


        String sql = "INSERT INTO STUFF (ID,LEVEL,SCORE,NUMBER_ENEMY,DIFFICULTY," +
                     "PLAYER_LIFE," +
                     "ENEMY1_LIFE," +
                     "ENEMY2_LIFE," +
                     "ENEMY3_LIFE," +
                     "ENEMY4_LIFE," +
                     "ENEMY5_LIFE," +
                     "ENEMY6_LIFE," +
                     "ENEMY7_LIFE," +
                     "PLAYER_X,PLAYER_Y,"+
                     "ENEMY1_X,ENEMY1_Y,"+
                     "ENEMY2_X,ENEMY2_Y,"+
                     "ENEMY3_X,ENEMY3_Y,"+
                     "ENEMY4_X,ENEMY4_Y,"+
                     "ENEMY5_X,ENEMY5_Y,"+
                     "ENEMY6_X,ENEMY6_Y,"+
                     "ENEMY7_X,ENEMY7_Y) " +
                     "VALUES (" + ID + ", " + LEVEL + ", " + SCORE + ", " + NUMBER_ENEMY + ", " + DIFFICULTY  +
                              enemyStr + ");";
        statement.executeUpdate(sql);
    }

    public void updateDataBase (int ID, int LEVEL, int SCORE, int DIFFICULTY, Player player, Vector <Enemy> enemies) throws SQLException {
        int NUMBER_ENEMY = enemies.size();
        String sql;
        sql = "UPDATE STUFF set LEVEL = " + LEVEL + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        sql = "UPDATE STUFF set SCORE = " + SCORE + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        sql = "UPDATE STUFF set DIFFICULTY = " + DIFFICULTY + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        sql = "UPDATE STUFF set NUMBER_ENEMY = " + NUMBER_ENEMY + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        float playerx = -1;
        float playery = -1;
        int playerlife = -1;

        if (NUMBER_ENEMY != 0){
            playerx = player.getPos().x;
            playerlife = player.getLife();
            playery = player.getPos().y;
        }

        sql = "UPDATE STUFF set PLAYER_X = " + playerx + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        sql = "UPDATE STUFF set PLAYER_Y = " + playery + " where ID=" + ID + ";";
        statement.executeUpdate(sql);

        sql = "UPDATE STUFF set PLAYER_LIFE = " + playerlife + " where ID=" + ID + ";";
        statement.executeUpdate(sql);
        int contor = 0;

        for (Enemy enemy : enemies){
            contor++;
            float enemyx = enemy.getPos().x;
            float enemyy = enemy.getPos().y;
            int enemylife = enemy.getLife();

            sql = "UPDATE STUFF set ENEMY" + contor + "_X = " + enemyx + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
            sql = "UPDATE STUFF set ENEMY" + contor + "_Y = " + enemyy + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
            sql = "UPDATE STUFF set ENEMY" + contor + "_LIFE = " + enemylife + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
        }

        for (int i = NUMBER_ENEMY + 1; i <= 7; i++){
            contor++;
            float enemyx = -1;
            float enemyy = -1;
            int enemylife = -1;

            sql = "UPDATE STUFF set ENEMY" + contor + "_X = " + enemyx + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
            sql = "UPDATE STUFF set ENEMY" + contor + "_Y = " + enemyy + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
            sql = "UPDATE STUFF set ENEMY" + contor + "_LIFE = " + enemylife + " where ID=" + ID + ";";
            statement.executeUpdate(sql);
        }
    }

    public void LoadDataBase () throws SQLException {
        numberOfEntries = 0;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STUFF");
        databaseElements = null;
        databaseElements = new Vector<>();

        while (resultSet.next()){
            numberOfEntries++;
            int ID = resultSet.getInt("ID");
            int LEVEL = resultSet.getInt("LEVEL");
            int SCORE = resultSet.getInt("SCORE");
            int NUMBER_ENEMY = resultSet.getInt("NUMBER_ENEMY");
            int DIFFICULTY = resultSet.getInt("DIFFICULTY");
            float PLAYER_X = -1;
            float PLAYER_Y = -1;
            int PLAYER_LIFE = -1;

            if (NUMBER_ENEMY != 0){
                PLAYER_X = resultSet.getFloat("PLAYER_X");
                PLAYER_Y = resultSet.getFloat("PLAYER_Y");
                PLAYER_LIFE = resultSet.getInt("PLAYER_LIFE");
            }

            DatabaseElement databaseElement = new DatabaseElement(ID, LEVEL, SCORE, NUMBER_ENEMY, DIFFICULTY,
                                new Vector2f(PLAYER_X, PLAYER_Y), PLAYER_LIFE);

            for (int i = 1; i <= NUMBER_ENEMY; i++){
                float enemy_x = resultSet.getFloat(("ENEMY" + i + "_X"));
                float enemy_y = resultSet.getFloat(("ENEMY" + i + "_Y"));
                int enemy_life = resultSet.getInt(("ENEMY" + i + "_LIFE"));
                databaseElement.addEnemy(new Vector2f(enemy_x, enemy_y), enemy_life);
            }
            databaseElements.add(databaseElement);
        }
    }

    public int getNumberOfEntries (){
        return numberOfEntries;
    }
    public Vector <DatabaseElement> getDatabaseElements (){
        return databaseElements;
    }
}
