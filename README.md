# OHJEET
## Sovelluksen käynnistys
1. Lataa projekti zip-tiedostona ja pura se haluamaasi paikkaan.
2. Avaa projekti IntelliJ IDEA:lla valitsemalla projektin pom.xml tiedosto.
3. Avaa terminaali ja syötä seuraava komento:
```
./mvnw spring-boot:run
```
4. Kun sovellus on valmis terminaalissa pitäisi näkyä "Application running at http://localhost:8080/", avaa kyseinen linkki.
5. Username on "user", salasana löytyy terminaalista ylempää kohdassa "Using generated security password:"
## Sovelluksen käyttö
### Henkilön lisääminen
1. Syötä nimi ja syntymäaika kenttiin "Name" ja "Date of Birth"
2. Paina "Add Person" lisätäksesi henkilön tietokantaan.
### Mittauksen lisääminen
1. Valitse jokin lisäämistäsi henkilöistä ylemmästä taulusta.
2. Lisää tiedot alimpiin kenttiin.
3. Paina "Add Measurement" lisätäksesi mittauksen kyseisen henkilön tietoihin.
### Suodatus
1. Etsi nimellä syöttämällä kenttään "Search by name" haluamasi nimi.
2. Ylempi taulu näyttää vain tiedot jotka sisältää kyseisen nimen.
## Itsearviointi
Toteutuksen perusteella pyrin 8 pisteeseen. Suurin aika työstä meni SecurityConfig:in kanssa taistelusta, sen kanssa oli useampi ongelma ennenkuin päädyin versioon joka toimi odotetusti.<br><br>
Täytetyt vaatimukset:
- Kaksi entiteettiä (Person.java ja Measurement.java) joiden välillä on relaatio. 2p
- Yksinkertainen suodatus yhdelle sarakkeelle. 1p
- Globaalien tyylien muuttaminen (Sivulla on "dark mode" teema). 1p
- SPA-sovellus, jossa on päänäkymä (Kirjautumissivun jälkeen sovellus toimii yhdellä sivulla). 1p
- Security-palikan käyttöönotto ja sisäänkirjautumissivun luominen (Default sisäänkirjautumis sivu joka tulee Spring Security:n mukana). 2p
- Työ julkaistuna Git:iin. 1p
