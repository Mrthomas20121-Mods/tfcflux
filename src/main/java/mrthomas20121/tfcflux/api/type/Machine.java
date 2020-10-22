package mrthomas20121.tfcflux.api.type;

public enum Machine {
    CRUSHER(0),
    STEAM_CRUSHER(1);

    private int id;

    private Machine(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Machine from(int id)
    {
        Machine result = null;
        for(Machine machine : Machine.values())
        {
            if(machine.id == id)
            {
                result = machine;
                break;
            }
        }
        return result;
    }
}
