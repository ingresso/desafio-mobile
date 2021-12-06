package com.jeanbarrossilva.ingresso.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Movie(
    val id: Long,
    val premiereDate: Date?,
    val title: Title,
    val imageUrl: String,
    val genres: List<String>,
    val score: Score?,
    val country: String,
    val director: String,
    val cast: List<String>,
    val distributor: String?,
    val synopsis: String,
    val url: String
): Parcelable {
    operator fun contains(other: String): Boolean {
        return other in title ||
            country.contains(other, ignoreCase = true) ||
            director.contains(other, ignoreCase = true) ||
            cast.any { actor -> actor.contains(other, ignoreCase = true) } ||
            distributor?.contains(other, ignoreCase = true) == true ||
            synopsis.contains(other, ignoreCase = true)
    }

    @Suppress("SpellCheckingInspection")
    companion object {
        val samples = listOf(
            Movie(
                id = 24405,
                premiereDate = null,
                Title("Spencer"),
                imageUrl = "https://ingresso-a.akamaihd.net/prd/img/movie/spencer/f27c5799-bd94-4698-b965-638ec2dc3e68.jpg",
                genres = listOf("Ficção"),
                Score(critics = 84, audience = 50),
                country = "Reino Unido",
                director = "Pablo Larraín",
                cast = listOf("Kristen Stewart", "Sean Harris", "Sally Hawkins"),
                distributor = "Diamond Films",
                synopsis = "O filme retrata  Diana, Princesa de Gales, e segue a decisão dela de terminar seu casamento com " +
                    "Charles, Príncipe de Gales, para deixar de ser membro da Família Real Britânica.",
                url = "https://www.ingresso.com/rio-de-janeiro/desafio/filmes/spencer?ing_source=api&ing_medium=link-filme" +
                    "&ing_campaign=desafio&ing_content="
            ),
            Movie(
                id = 24453,
                premiereDate = null,
                Title("Jogos Mortais -  Teatro LPV"),
                imageUrl = "https://ingresso-a.akamaihd.net/prd/img/movie/jogos-mortais-teatro-lpv/f4538e50-2895-48af-8508-" +
                    "4dc01d711775.jpg",
                score = null,
                genres = listOf("Teatro Contemporâneo"),
                country = "Brasil",
                director = "Vado Ferreira",
                cast = emptyList(),
                distributor = null,
                synopsis = "O teatro jogos mortais foi criado há 7 anos, com o intuito de ajudar a nossa sociedade através do " +
                    "teatro, abordando assuntos como abuso sexual, violência doméstica drogas, bebidas, suicídio entre outros temas " +
                    "presentes no nosso cotidiano e que atingido crianças e adultos corroendo milhares de famílias, é um teatro " +
                    "realista, aonde muitas pessoas infelizmente tem se identificado.\\nFazemos parte da igreja do evangelho " +
                    "quadrangular, onde tentamos ajudar pessoas a entenderem que nem tudo está perdido e que independentemente da " +
                    "situação, ainda há esperança de dias melhores para nós e nossas famílias.\\n",
                url = "https://www.ingresso.com/rio-de-janeiro/desafio/filmes/jogos-mortais-teatro-lpv?ing_source=api" +
                    "&ing_medium=link-filme&ing_campaign=desafio&ing_content="
            )
        )
    }
}