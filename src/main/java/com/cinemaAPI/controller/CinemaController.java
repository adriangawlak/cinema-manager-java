package com.cinemaAPI.controller;

//import cinema.exception.WrongPasswordException;
import com.cinemaAPI.entity.Cinema;
import com.cinemaAPI.entity.Seat;
import com.cinemaAPI.exceptions.WrongPasswordException;
import com.cinemaAPI.models.ReturnedTicket;
import com.cinemaAPI.models.Stats;
import com.cinemaAPI.models.Ticket;
import com.cinemaAPI.models.Token;
import com.cinemaAPI.service.CinemaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;

    //    @Autowired
    public CinemaController(CinemaService cinemaService) {
//        this.cinema = cinema;
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema showCinema() {
        return cinemaService.getCinema();
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Seat seat) {
        return cinemaService.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Token token) {
        Seat returnedSeat = cinemaService.returnTicket(token.getToken());

        return new ReturnedTicket(returnedSeat);
    }

    @PostMapping("/stats")  // password -> http://localhost:8080/stats?password=super_secret
    public Stats showStats(@RequestParam(required = false) String password, HttpServletRequest request) {
        if (request.getParameterMap().isEmpty() || !password.equals("super_secret"))
            throw new WrongPasswordException();

        return cinemaService.calculateStats();
    }

}

// To check on postman type:
// http://localhost:8080/purchase

// Request body:
//        {
//        "row": 1,
//        "column": 1
//        }