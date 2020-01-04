package es.nemesis.helper

class Board(typeAlien: String)
{
    lateinit var alienBag : IAliensBag

    init
    {
        if(typeAlien.equals("Intruders"))
        {
            alienBag = IntrudersBag()
            alienBag.initGameBag(4)
        }
    }
}