package com.moviestore.checkpoint.domain.director;

import com.moviestore.checkpoint.util.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directors")
@RequiredArgsConstructor
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    Patcher patcher;

    @GetMapping("/get/all")
    public ResponseEntity<List<DirectorBasicDTO>> getAll() {
        List<Director> directorList = directorService.getAll();
        List<DirectorBasicDTO> directorBasicDTOList = directorList.stream().map(DirectorBasicDTO::mapFromEntity).toList();
        return new ResponseEntity<>(directorBasicDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/{directorId}")
    public ResponseEntity<DirectorDTO> getById(@PathVariable("directorId") Long directorId) {
        Director foundDirector = directorService.getById(directorId);
        DirectorDTO directorDTO = DirectorDTO.mapFromEntity(foundDirector);
        return new ResponseEntity<>(directorDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DirectorBasicDTO> add(@RequestBody Director director) {
        Director createdDirector = directorService.add(director);
        DirectorBasicDTO directorBasicDTO = DirectorBasicDTO.mapFromEntity(createdDirector);
        return new ResponseEntity<>(directorBasicDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{directorId}")
    public ResponseEntity<DirectorBasicDTO> updateDirector(@RequestBody Director incompleteDirector, @PathVariable("directorId") Long directorId) {
        Director foundDirector = directorService.getById(directorId);

        try {
            patcher.elementPatcher(foundDirector, incompleteDirector);
            directorService.add(foundDirector);
        }catch(Exception e) {
            e.printStackTrace();
        }
        DirectorBasicDTO directorBasicDTO = DirectorBasicDTO.mapFromEntity(foundDirector);
        return new ResponseEntity<>(directorBasicDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{directorId}")
    public ResponseEntity<Void> delete(@PathVariable("directorId") Long directorId) {
        directorService.delete(directorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
