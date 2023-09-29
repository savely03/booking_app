package com.github.savely03.bookingapp.contoller;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.dto.HotelFilterDto;
import com.github.savely03.bookingapp.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String create(@Valid HotelDto hotelDto) {
        HotelDto createdHotel = hotelService.create(hotelDto);
        return "redirect:/hotels/" + createdHotel.id();
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String update(@PathVariable Long id, @Valid HotelDto hotelDto) {
        hotelService.update(id, hotelDto);
        return "redirect:/hotels/" + id;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String findById(Model model, @PathVariable Long id) {
        HotelDto hotelDto = hotelService.findById(id);
        model.addAttribute("hotel", hotelDto);
        return "hotels/hotel";
    }

    @GetMapping("/free")
    public String findAllFreeByFilter(Model model, @Valid HotelFilterDto filter) {
        model.addAttribute("hotels", hotelService.findAllFreeByFilter(filter));
        model.addAttribute("filter", filter);
        return "hotels/hotels-free-by-filter";
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('MANAGER')")
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
    @PreAuthorize("hasAuthority('MANAGER')")
    public String getFormForCreateHotels() {
        return "hotels/hotels-create";
    }
}
