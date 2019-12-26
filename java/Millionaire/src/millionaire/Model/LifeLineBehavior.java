package millionaire.Model;

/**
 * @author Mohammad
 * This interface represents life line behavior and it is created to use strategy design pattern.
 */
interface LifeLineBehavior {
    // The following behavior needed to be able reach game object.
    Game game = Game.getInstance();

    /**
     * It runs the behavior of a life line.
     *
     * @return string array that store a result of using a life line.
     */
    String[] toDo();

}
