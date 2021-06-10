package br.senai.sp.jandira.imcapp20

import java.util.*

fun getDicaDoDiaNcd(): String{
    val dicas = listOf<String>(
        "Retire toda a gordura visível das carnes antes de cozinhá-las.",
        "Evite açúcar e farinha branca.",
        "Beba muita água durante o dia, mas não durante as refeições, pois atrapalha a digestão e distende o abdômen.",
        "Consuma alimentos integrais, que são ricos em fibras, retardam a absorção dos carboidratos e são mais nutritivos que os refinados."
    )

    val sorteio = Random()
    val dica = sorteio.nextInt(dicas.size)

    return dicas[dica]
}