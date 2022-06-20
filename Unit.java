public class Unit
{
    protected int atkRating;
    protected int defRating;
    protected boolean armor;
    protected boolean prestrike;
    protected int mobility;
    protected int cost;

    public Unit()
    {
        atkRating = 0;
        defRating = 0;
        armor = false;
        prestrike = false;
        mobility = 0;
    }
    
    public int getAtkRating()
    {
        return atkRating;
    }

    public int getDefRating()
    {
        return defRating;
    }

    public boolean getArmor()
    {
        return armor;
    }

    public boolean getPrestrike()
    {
        return prestrike;
    }

    public int getMobility()
    {
        return mobility;
    }

    public String toString()
    {
        return "Attack Rating: "+atkRating+", Defense Rating: "+defRating+", Armor: "+armor+", Prestrike: "+prestrike+", Mobility: "+mobility;
    }
}
