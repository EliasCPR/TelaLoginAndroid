package br.senai.sp.jandira.imcapp20

fun calcularNdc(genero:Char, peso:Double, idade:Int, nivelAtividade:Int):Double{

    ////  NCD PARA SEXO MACULINO    ///
    if (genero == 'm'){

        //// NIVEL DE ATIVIDADE NENHUMA ///
        if (nivelAtividade == 0) {
            if (idade == 0) {
                return 15.3*peso + 679
            }else if (idade == 1) {
                return 11.6*peso + 879
            }else {
                return 13.5*peso + 487
            }

            //// NIVEL DE ATIVIDADE LEVE    ///
        }else if (nivelAtividade == 1) {
            if (idade == 0) {
                return (15.3*peso + 679)*1.5
            }else if (idade == 1) {
                return (11.6*peso + 879)*1.5
            }else{
                return (13.5*peso + 487)*1.5
            }

            //// NIVEL DE ATIVIDADE MODERADA///
        }else if (nivelAtividade == 2) {
            if (idade == 0) {
                return (15.3*peso + 679)*1.8
            }else if (idade == 1) {
                return (11.6*peso + 879)*1.8
            }else {
                return (13.5*peso + 487)*1.8
            }

            //// NIVEL DE ATIVIDADE INTENSA ///
        }else if (nivelAtividade == 3) {
            if (idade == 0) {
                return (15.3*peso + 679)*2.1
            }else if (idade == 1) {
                return (11.6*peso + 879)*2.1
            }else {
                return (13.5*peso + 487)*2.1
            }
        }

        ////  NCD DO SEXO FEMININO      ///
    }else{
        //// NIVEL DE ATIVIDADE NENHUMA ///
        if (nivelAtividade == 0) {
            if (idade == 0) {
                return 14.7*peso + 496
            }else if (idade == 1) {
                return 8.7*peso + 829
            }else{
                return 10.5*peso + 596
            }

            //// NIVEL DE ATIVIDADE LEVE    ///
        }else if (nivelAtividade == 1) {
            if (idade == 0) {
                return (14.7*peso + 496)*1.6
            }else if (idade == 1) {
                return (8.7*peso + 829)*1.6
            }else {
                return (10.5*peso + 596)*1.6
            }

            //// NIVEL DE ATIVIDADE MODERADA ///
        }else if (nivelAtividade == 2) {
            if (idade == 0) {
                return (14.7*peso + 496)*1.6
            }else if (idade == 1) {
                return (8.7*peso + 829)*1.6
            }else{
                return (10.5*peso + 596)*1.6
            }

            //// NIVEL DE ATIVIDADE INTENSA ///
            }else if (nivelAtividade == 3) {
                if (idade == 0) {
                    return (14.7*peso + 496)*1.8
                }else if (idade == 1) {
                    return (8.7*peso + 829)*1.8
                }else {
                    return (10.5*peso + 596)*1.8
                }
            }
    }

    return 0.0
}