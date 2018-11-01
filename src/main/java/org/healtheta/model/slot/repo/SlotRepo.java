package org.healtheta.model.slot.repo;

import org.healtheta.model.common.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.healtheta.model.common.Reference;
import org.healtheta.model.slot.Slot;

@Repository
public interface SlotRepo extends JpaRepository<Slot, Long> {
    public Slot findSlotById(Long id);
    public Slot findSlotByIdentifier(Identifier identifier);
    public List<Slot> findSlotBySchedule(Reference schedule);
}
