package os.marjanepromotionapisb.Service.Implementation.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import os.marjanepromotionapisb.Entity.*;
import os.marjanepromotionapisb.Repository.*;
import os.marjanepromotionapisb.Service.Implementation.CinemaService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public void initVilles() {
        Stream.of("Casa", "Rabat", "Safi").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("MegaRama", "EMax", "Founoun").forEach(cinemaName->{
                Cinema cinema = new Cinema();
                cinema.setName(cinemaName);
                cinema.setNombreSalle(3*(int)(Math.random()*7));
                cinema.setVille(v);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i=0; i< cinema.getNombreSalle(); i++){
                Salle salle = new Salle();
                salle.setName("Selle: "+i+1);
                salle.setCinema(cinema);
                salle.setNombrePlace(20+(int)(Math.random()*55));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i=0; i< salle.getNombrePlace(); i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });

    }

    @Override
    public void iniCategories() {
        Stream.of("Drama", "Action", "Fiction", "Histoire").forEach(c->{
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categoryRepository.save(categorie);
        });
    }

    @Override
    public void intFilms() {
        double[] durees = new double[] {1,1.5,2,2.5,3,4};
        List<Categorie> categories = categoryRepository.findAll();
        Stream.of("12 Homes en colaire", "Forrser Gump", "Green Book", "La Ligne Verte", " La Pari").forEach(titre->{
            Film film = new Film();
            film.setTitre(titre);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(titre.replace(" ", ""));
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:30", "13:00", "15:00", "16:00", "20:00").forEach(seance->{
            Seance seance1 = new Seance();
            try {
                seance1.setHeureDebut(dateFormat.parse(seance));
                seanceRepository.save(seance1);
            }catch (ParseException e){
                e.printStackTrace();
            }
        });

    }




    @Override
    public void initProjections() {
        double[] prices = new double[] {222, 120.50, 140.90, 130,230};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll().forEach(s -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setSalle(salle);
                            projection.setSeance(s);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
            projectionRepository.findAll().forEach(projection -> {
                projection.getSalle().getPlaces().forEach(place -> {
                    Ticket ticket = new Ticket();
                    ticket.setProjection(projection);
                    ticket.setPrix(projection.getPrix());
                    ticket.setPlace(place);
                    ticket.setReserver(false);
                    ticketRepository.save(ticket);
                });
            });
    }
}
