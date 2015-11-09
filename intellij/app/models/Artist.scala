package models

/**
  * Created by davidraleigh on 11/8/15.
  */
case class Artist(name: String, country: String)

object Artist {
  val m_availableArtist = Seq(
    Artist("Wolfgang Amadeus Mozart","Austria"),
    Artist("Ludwig van Beethoven", "Germany"),
    Artist("Johann Sebastian Bach", "Germany"),
    Artist("Frédéric François Chopin", "Poland"),
    Artist("Joseph Haydn", "Austria"),
    Artist("Antonio Lucio Vivaldi", "Italy"),
    Artist("Franz Peter Schubert", "Austria"),
    Artist("Franz Liszt", "Austria"),
    Artist("Giuseppe Fortunino Francesco Verdi", "Austria")
  )

  def fetch: Seq[Artist] = {
    m_availableArtist
  }

  def fetchByName(name: String): Seq[Artist] = {
    m_availableArtist.filter(a => a.name.contains(name))
  }

  def fetchByCountry(country: String): Seq[Artist] = {
    m_availableArtist.filter(a => a.country == country)
  }

  def fetchByNameOrCountry(name: String, country: String): Seq[Artist] = {
    m_availableArtist.filter(a => a.name.contains(name) || a.country == country)
  }

  def fetchByNameAndCountry(name: String, country: String): Seq[Artist] = {
    m_availableArtist.filter(a => a.name.contains(name) && a.country == country)
  }

}
