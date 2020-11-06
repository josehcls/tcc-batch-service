package br.com.fermentis.tccrecipeservice.controller.v1;

import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping()
    public ResponseEntity<BatchDTO> createBatch (@RequestBody BatchDTO batchDTO) throws Exception {
        return ResponseEntity.ok(batchService.createBatch(batchDTO));
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<BatchDTO> getBatch(@PathVariable Long batchId) throws Exception {
        return ResponseEntity.ok(batchService.getBatch(batchId));
    }

    @PutMapping("/{batchId}")
    public ResponseEntity<BatchDTO> updateBatch(@PathVariable Long batchId,
                                                  @RequestBody BatchDTO batchDTO) throws Exception {
        return ResponseEntity.ok(batchService.updateBatch(batchId, batchDTO));
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity deleteBatch(@PathVariable Long batchId) throws Exception {
        batchService.deleteBatch(batchId);
        return ResponseEntity.ok().build();
    }
}
