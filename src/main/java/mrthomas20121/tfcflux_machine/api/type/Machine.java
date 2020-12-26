package mrthomas20121.tfcflux_machine.api.type;

import mrthomas20121.tfcflux_machine.objects.tiles.machines.TECrusher;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import net.minecraft.tileentity.TileEntity;

public enum Machine {
    ELECTRIC_FURNACE(0, false),
    CRUSHER(1, false),
    STEAM_CRUSHER(2, true);

    private int id;
    private int hardness;
    private String machineName;

    private Machine(int id, boolean steam)
    {
        this.id = id;
        this.hardness = 6;
        this.machineName = steam? "steam_machine/"+this.name().toLowerCase() : "machine/"+this.name().toLowerCase();
    }

    private Machine(int id, boolean steam, int hardness)
    {
        this(id, steam);
        this.hardness = hardness;
    }

    public int getId() {
        return id;
    }

    public int getHardness() {
        return hardness;
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

    public String getMachineName() {
        return machineName;
    }

    @SuppressWarnings("unchecked")
    public <T extends TileEntity> T getTileEntity()
    {
        switch (id)
        {
            case 0:
                return (T) new TEElectricFurnace();
            case 1:
                return (T) new TECrusher();
        }
        return null;
    }
}
