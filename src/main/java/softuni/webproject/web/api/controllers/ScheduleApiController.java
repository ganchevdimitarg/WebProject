package softuni.webproject.web.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.webproject.services.models.ScheduleServiceModel;
import softuni.webproject.services.services.schedule.ScheduleService;
import softuni.webproject.web.api.models.ScheduleResponseModel;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class ScheduleApiController {
    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

//    @GetMapping(value = "/api/schedule")
//    public ResponseEntity<List<ScheduleResponseModel>> getSchedule(HttpSession session) {
//        List<ScheduleResponseModel> result = scheduleService.getAll()
//                .stream()
//                .map(s -> modelMapper.map(s, ScheduleResponseModel.class))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }




}
