package es.nemesis.helper

const val QUEEN = 4
const val BLANK = 0

const val EMPTY = "No hay Intruders en la bolsa"
const val ALIENBAG = "Se ha sacado un alien de tipo %s con iniciativa %s"
const val NEWALIEN = "Se ha añadido un alien de tipo %s"
const val RUIDO = "Tirada de ruido"
const val QUEENATTACK = "Ataque reina"

class IntrudersBag : IAliensBag
{
    val isQueen : Boolean
        get() = game.containsKey("Queen")

    val isBlank : Boolean
        get() = game.containsKey("Blank")

    var totalBreeder : HashMap<String, Int> = hashMapOf("Breeder_1" to 3, "Breeder_2" to 4)
    var totalLarvae : HashMap<String, Int> = hashMapOf("Larvae_1" to 1, "Larvae_2" to 1, "Larvae_3" to 1, "Larvae_4" to 1,
        "Larvae_5" to 1, "Larvae_6" to 1, "Larvae_7" to 1, "Larvae_8" to 1)
    var totalCreeper : HashMap<String, Int> = hashMapOf("Creeper_1" to 1, "Creeper_2" to 1, "Creeper_3" to 1)
    var totalAdult : HashMap<String, Int> = hashMapOf("Adult_1" to 2, "Adult_2" to 2, "Adult_3" to 2, "Adult_4" to 2,
        "Adult_5" to 3, "Adult_6" to 3, "Adult_7" to 3, "Adult_8" to 3, "Adult_9" to 3,
        "Adult_10" to 4, "Adult_11" to 4, "Adult_12" to 4)

    override var game: HashMap<String, Int> = HashMap<String, Int>()

    override fun initGameBag(players : Int)
    {
        game.put("Queen", QUEEN)
        game.put("Blank", BLANK)

        for(x in 1..4)
        {
            putLarvaeInGame()
        }

        putCreeperInGame()

        val total = 3 + players

        for(x in 1..total)
        {
            putAdultInGame()
        }
    }

    private fun putAdultInGame()
    {
        val max : Int = totalAdult.size - 1

        if(totalAdult.size == 0)
        {
            return
        }


        val randomInteger = (0..max).shuffled().first()
        val key : String = (totalAdult.keys.toTypedArray())[ randomInteger ]

        val initiative = totalAdult.get(key)
        game.put(key, (initiative as Int))
        totalAdult.remove(key)
    }

    private fun putCreeperInGame()
    {
        val max : Int = totalCreeper.size - 1

        if(totalCreeper.size == 0)
        {
            return
        }

        val randomInteger = (0..max).shuffled().first()
        val key : String = (totalCreeper.keys.toTypedArray())[ randomInteger ]

        val initiative = totalCreeper.get(key)
        game.put(key, (initiative as Int))
        totalCreeper.remove(key)
    }

    private fun putBreederInGame()
    {
        val max : Int = totalBreeder.size - 1

        if(totalBreeder.size == 0)
        {
            return
        }

        val randomInteger = (0..max).shuffled().first()
        val key : String = (totalBreeder.keys.toTypedArray())[ randomInteger ]

        val initiative = totalBreeder.get(key)
        game.put(key, (initiative as Int))
        totalBreeder.remove(key)
    }

    private fun putLarvaeInGame()
    {
        val max : Int = totalLarvae.size - 1

        if(totalLarvae.size == 0)
        {
            return
        }

        val randomInteger = (0..max).shuffled().first()
        val key : String = (totalLarvae.keys.toTypedArray())[ randomInteger ]

        val initiative = totalLarvae.get(key)
        game.put(key, (initiative as Int))
        totalLarvae.remove(key)
    }

    private fun getAlienBag() : Triple<String, String, Int>
    {
        val max : Int = game.size - 1

        println("takeAlienGame :  " + game.size)

        if(game.size == 0)
        {
            return Triple("", "", 0)
        }

        val randomInteger = (0..max).shuffled().first()
        val key : String = (game.keys.toTypedArray())[ randomInteger ]
        val initiative : Int? = game.get(key)

        val type : String
        val fin : Int = key.indexOf("_")

        if(fin == -1)
        {
            type = key
        }
        else
        {
            type = key.substring(0, key.indexOf("_"))
        }

        println(type)

        return Triple(type, key, (initiative as Int))
    }

    override fun alienBagDevelopment() : Pair<String, String>
    {
        val (type, key, initiative) = getAlienBag()

        when(type)
        {
            "Larvae" ->
            {
                game.remove(key)
                putAdultInGame()
                return Pair("larvae", String.format(NEWALIEN, "adulto"))
            }
            "Creeper" ->
            {
                game.remove(key)
                putBreederInGame()
                return Pair("creeper", String.format(NEWALIEN, "breeder"))
            }
            "Adult" ->
            {
                return Pair("adult_" + initiative, RUIDO)
            }
            "Breeder" ->
            {
                return Pair("breeder_" + initiative, RUIDO)
            }
            "Queen" ->
            {
                return Pair("queen", QUEENATTACK)
            }
            "Blank" ->
            {
                putAdultInGame()
                return Pair("blank", String.format(NEWALIEN, "adulto"))
            }
            else ->
            {
                return Pair("", EMPTY)
            }
        }
    }

    override fun alienBagNoiseRoll() : Pair<String, String>
    {
        val (type, key, initiative) = getAlienBag()

        when(type)
        {
            "Larvae" ->
            {
                game.remove(key)
                putAdultInGame()
                return Pair("larvae", String.format(NEWALIEN, "adulto"))
            }
            "Creeper" ->
            {
                game.remove(key)
                putBreederInGame()
                return Pair("creeper", String.format(NEWALIEN, "breeder"))
            }
            "Adult" ->
            {
                return Pair("adult_" + initiative, RUIDO)
            }
            "Breeder" ->
            {
                return Pair("breeder_" + initiative, RUIDO)
            }
            "Queen" ->
            {
                return Pair("queen", QUEENATTACK)
            }
            "Blank" ->
            {
                putAdultInGame()
                return Pair("blank", String.format(NEWALIEN, "adulto"))
            }
            else ->
            {
                return Pair("", EMPTY)
            }
        }
    }

    override fun putAlienGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun takeAlienTotal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putAlienTotal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}