package org.healtheta.web;

import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.OperationOutcome;
import org.healtheta.model.common.Reference;
import org.healtheta.model.common.repos.IdentifierRepo;
import org.healtheta.model.common.repos.ReferenceRepo;
import org.healtheta.model.slot.Slot;
import org.healtheta.model.slot.repo.SlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SlotController {
    @Autowired
    SlotRepo slotRepo;
    @Autowired
    private IdentifierRepo identifierRepo;
    @Autowired
    private ReferenceRepo referenceRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Slot slot){
        Identifier tmpId = slot.getIdentifier();
        if(tmpId.getValue() == null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InvalidParameter(),
                    HttpStatus.OK);
        }

        if(identifierRepo.findIdentifierByValue(tmpId.getValue()) != null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordExists(),
                    HttpStatus.OK);
        }

        try{
            Slot tmp = new Slot();
            tmp = slotRepo.save(tmp);
            slot.setId(tmp.getId());

            Reference ref = new Reference();
            ref.setIdentifier(slot.getIdentifier());
            ref.setDisplay("slot-reference");
            slot.setReference(ref);
            slot = slotRepo.save(slot);
            return new ResponseEntity<Slot>(slot, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable String id){
        try{
            Long lId = Long.parseLong(id);
            Slot slot = slotRepo.findSlotById(lId);
            if(slot != null){
                return new ResponseEntity<Slot>(slot, HttpStatus.OK);
            }else{
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Slot slot){
        Long id = slot.getId();
        Slot tmp = slotRepo.findSlotById(id);
        if ( tmp != null){
            //record exists let;s update.
            //reference and identifiers are not to be updated.
            slot.setIdentifier(tmp.getIdentifier());
            slot.setReference(tmp.getReference());
            slot = slotRepo.save(slot);
            return new ResponseEntity<Slot>(slot, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> delete(String id) {
        return null;
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "schedule", required=false) Long schedule){
        try {
            if (schedule != null) {
                Reference ref = referenceRepo.findReferenceById(schedule);
                if (ref != null) {
                    List<Slot> slotList = slotRepo.findSlotBySchedule(ref);
                    return new ResponseEntity<List>(slotList, HttpStatus.OK);
                }else{
                    return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(),
                            HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<OperationOutcome>(OperationOutcome.InvalidParameter(),
                        HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/format", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> format(){
        Slot slot = new Slot();
        Identifier identifier = new Identifier();
        identifier.setValue("SLOT-AB80999-DE99999999");
        slot.setIdentifier(identifier);
        return new ResponseEntity<Slot>(slot,HttpStatus.OK);
    }
}
