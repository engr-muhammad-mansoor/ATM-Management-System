package TechLiftProject.ATMManagementSystem.Controllers;

import TechLiftProject.ATMManagementSystem.Services.UpdatePinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path="/atm")
public class UpdatePinController {
    @Autowired
    UpdatePinService changePinService;
    @PutMapping("/update-pin/{pin}")
    public String updatePin(@PathVariable("pin") int pin)
    {
        return changePinService.updatePin(pin);
    }
}