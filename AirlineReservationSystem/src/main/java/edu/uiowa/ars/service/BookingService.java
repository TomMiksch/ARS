package edu.uiowa.ars.service;

import edu.uiowa.ars.service.EntityManagementService;
import edu.uiowa.ars.model.Booking;

public interface BookingService extends EntityManagementService<Booking> {
    
    void confirmEntityByEmail(String email);
    
}
