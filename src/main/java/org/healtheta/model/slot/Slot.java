package org.healtheta.model.slot;

import org.healtheta.model.common.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Date;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Slot")
public class Slot {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(unique=true, name = "_identifier")
    private Identifier identifier;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_serviceCategory")
    private CodeableConcept serviceCategory;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_serviceType")
    private List<CodeableConcept> serviceType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_speciality")
    private List<CodeableConcept> speciality;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_appointmentType")
    private CodeableConcept appointmentType;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_schedule")
    private Reference schedule;

    @Column(name = "_status")
    private String status;

    @Column(name = "_start")
    private Date start;

    @Column(name = "_end")
    private Date end;

    @Column(name = "_overBooked")
    private boolean overBooked;

    @Column(name = "_comment")
    private boolean comment;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_reference")
    private Reference reference;

    public Long getId() {
        return id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public CodeableConcept getServiceCategory() {
        return serviceCategory;
    }

    public List<CodeableConcept> getServiceType() {
        return serviceType;
    }

    public List<CodeableConcept> getSpeciality() {
        return speciality;
    }

    public CodeableConcept getAppointmentType() {
        return appointmentType;
    }

    public Reference getSchedule() {
        return schedule;
    }

    public String getStatus() {
        return status;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isOverBooked() {
        return overBooked;
    }

    public boolean isComment() {
        return comment;
    }

    public Reference getReference() {
        return reference;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setServiceCategory(CodeableConcept serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public void setServiceType(List<CodeableConcept> serviceType) {
        this.serviceType = serviceType;
    }

    public void setSpeciality(List<CodeableConcept> speciality) {
        this.speciality = speciality;
    }

    public void setAppointmentType(CodeableConcept appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setSchedule(Reference schedule) {
        this.schedule = schedule;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setOverBooked(boolean overBooked) {
        this.overBooked = overBooked;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
