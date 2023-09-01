package model.functionality.api;

public interface ChessTimerInt {

    /**
     * Converts the given time in milliseconds to minutes and seconds.
     *
     * @param time The time in milliseconds to be converted.
     * @return A string representation of the time in the format "minutes:seconds".
     */
    String formatTime(long time);

    /**
     * Stops the timer.
     */
    void stopTimer();

    /**
     * Changes the turn of the player.
     */
    void switchTurn();
    
}
