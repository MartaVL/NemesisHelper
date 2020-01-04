package es.nemesis.helper

interface IAliensBag
{
    var game : HashMap<String, Int>

    fun initGameBag(players : Int)
    fun alienBagDevelopment() : Pair<String, String>
    fun alienBagNoiseRoll() : Pair<String, String>
    fun putAlienGame()
    fun takeAlienTotal()
    fun putAlienTotal()
}