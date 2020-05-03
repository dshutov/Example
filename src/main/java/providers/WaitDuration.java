package providers;

public enum WaitDuration
{
    ONE_SECOND(1),
    FIVE_SECONDS(5),
    FIFTEEN_SECONDS(15),
    THIRTY_SECONDS(30),
    SIXTY_SECONDS(60),
    LONG(300);

    private final int duration;

    WaitDuration(int duration)
    {
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

}
