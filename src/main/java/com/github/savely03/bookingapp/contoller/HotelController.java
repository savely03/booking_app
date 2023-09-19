package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    public String create(@Valid Hotel hotel) {
        Hotel createdHotel = hotelService.save(hotel);
        return "redirect:/hotels/" + createdHotel.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Valid Hotel hotel) {
        hotelService.update(id, hotel);
        return "redirect:/hotels/" + id;
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "hotels/hotel";
    }

    @GetMapping("/free")
    public String findAllFreeByFilter(Model model, @Valid HotelFilterDto filter) {
        model.addAttribute("hotels", hotelService.findAllFreeByFilter(filter));
        model.addAttribute("filter", filter);
        return "hotels/hotels-free-by-filter";
    }

    @GetMapping()
    public String findAllWithFullInfoByRooms(Model model,
                                             @RequestParam(value = "city", required = false) String city,
                                             @RequestParam(value = "stars", required = false) Short stars) {
        model.addAttribute("hotels", hotelService.findAllWithFullInfoByRooms(city, stars));
        return "hotels/hotels-all";
    }

    @GetMapping("/index")
    public String hotelsIndex() {
        return "hotels/hotels-index";
    }

    @GetMapping("/create")
    public String getFormForCreateHotels() {
        return "hotels/hotels-create";
    }

}
