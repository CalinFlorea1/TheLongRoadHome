package TheLongRoadHome;

import TheLongRoadHome.Window.Window;

public class Game {

    public Game (){
        Window.getInstance();
    }

    public static void main (String []Args){
        new Game();
    }
}
