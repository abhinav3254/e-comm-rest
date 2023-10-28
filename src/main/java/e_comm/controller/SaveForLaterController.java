package e_comm.controller;


import e_comm.modal.SaveForLater;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/save-for-later")
public interface SaveForLaterController {

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addInSaveForLater(@PathVariable Long productId);

    @GetMapping("/all")
    public ResponseEntity<SaveForLater> getAllSaveForLater();

    @PostMapping("/delete-product/{productId}")
    public ResponseEntity<String> deleteFromSaveForLater(@PathVariable Long productId);

    @PostMapping("/delete-product")
    public ResponseEntity<String> deleteSaveForLater();

}
