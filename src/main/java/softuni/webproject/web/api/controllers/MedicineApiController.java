package softuni.webproject.web.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.webproject.services.services.medicine.MedicineService;
import softuni.webproject.web.api.models.MedicineResponseModel;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class MedicineApiController {
    private final MedicineService medicineService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/api/medicine")
    public ResponseEntity<List<MedicineResponseModel>> getMedicine(HttpSession session) {
        List<MedicineResponseModel> result = medicineService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, MedicineResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
