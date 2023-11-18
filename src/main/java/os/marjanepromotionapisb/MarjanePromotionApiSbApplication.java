package os.marjanepromotionapisb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import os.marjanepromotionapisb.Service.Implementation.CinemaService;
import os.marjanepromotionapisb.Util.Enum.StatutEnum;

import java.util.stream.Stream;

@SpringBootApplication
public class MarjanePromotionApiSbApplication implements CommandLineRunner{
    @Autowired
    private CinemaService cinemaService;

    public static void main(String[] args) {
        SpringApplication.run(MarjanePromotionApiSbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
         cinemaService.initVilles();
         cinemaService.initCinemas();
         cinemaService.initSalles();
         cinemaService.initPlaces();
         cinemaService.initSeances();
         cinemaService.iniCategories();
         cinemaService.intFilms();
         cinemaService.initProjections();
         cinemaService.initTickets();
    }

}
