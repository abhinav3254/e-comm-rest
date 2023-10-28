package e_comm.controller.impl;

import e_comm.controller.SaveForLaterController;
import e_comm.modal.SaveForLater;
import e_comm.service.SaveForLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SaveForLaterControllerImpl implements SaveForLaterController {

    @Autowired
    private SaveForLaterService saveForLaterService;

    @Override
    public ResponseEntity<String> addInSaveForLater(Long productId) {
        return saveForLaterService.addInSaveForLater(productId);
    }

    @Override
    public ResponseEntity<SaveForLater> getAllSaveForLater() {
        return saveForLaterService.getAllSaveForLater();
    }

    @Override
    public ResponseEntity<String> deleteFromSaveForLater(Long productId) {
        return saveForLaterService.deleteFromSaveForLater(productId);
    }

    @Override
    public ResponseEntity<String> deleteSaveForLater() {
        return saveForLaterService.deleteSaveForLater();
    }
}
