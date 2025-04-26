package com.example.project.view;

import com.example.project.entity.Measurement;
import com.example.project.entity.Person;
import com.example.project.service.MeasurementService;
import com.example.project.service.PersonService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("")
@PermitAll
public class PersonView extends VerticalLayout {

    private final PersonService personService;
    private final MeasurementService measurementService;

    private final Grid<Person> personGrid = new Grid<>(Person.class, false);
    private final Grid<Measurement> measurementGrid = new Grid<>(Measurement.class, false);
    private final TextField filterField = new TextField();

    private final TextField nameField = new TextField("Name");
    private final TextField dobField = new TextField("Date of Birth");

    private final TextField timestampField = new TextField("Timestamp");
    private final NumberField systolicField = new NumberField("Systolic");
    private final NumberField diastolicField = new NumberField("Diastolic");
    private final NumberField weightField = new NumberField("Weight");

    private Person selectedPerson;

    @Autowired
    public PersonView(PersonService personService, MeasurementService measurementService) {
        this.personService = personService;
        this.measurementService = measurementService;

        setSizeFull();
        setSpacing(true);
        setPadding(true);

        configurePersonGrid();
        configureMeasurementGrid();

        filterField.setPlaceholder("Search by name");
        filterField.addValueChangeListener(e -> updateList());

        Button addPersonButton = new Button("Add Person", e -> addPerson());
        Button addMeasurementButton = new Button("Add Measurement", e -> addMeasurement());

        HorizontalLayout personForm = new HorizontalLayout(nameField, dobField, addPersonButton);
        HorizontalLayout measurementForm = new HorizontalLayout(timestampField, systolicField, diastolicField, weightField, addMeasurementButton);

        add(
                new Text("Person Registry"),
                filterField,
                personGrid,
                personForm,
                new Text("Measurements"),
                measurementGrid,
                measurementForm
        );

        updateList();
    }

    private void configurePersonGrid() {
        personGrid.addColumn(Person::getId).setHeader("ID").setAutoWidth(true);
        personGrid.addColumn(Person::getName).setHeader("Name").setAutoWidth(true);
        personGrid.addColumn(Person::getDateOfBirth).setHeader("Date of Birth").setAutoWidth(true);
        personGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        personGrid.setHeight("200px");

        personGrid.asSingleSelect().addValueChangeListener(event -> {
            selectedPerson = event.getValue();
            if (selectedPerson != null) {
                measurementGrid.setItems(measurementService.findByPerson(selectedPerson));
            } else {
                measurementGrid.setItems(List.of());
            }
        });
    }

    private void configureMeasurementGrid() {
        measurementGrid.addColumn(Measurement::getTimestamp).setHeader("Timestamp");
        measurementGrid.addColumn(Measurement::getSystolic).setHeader("Systolic");
        measurementGrid.addColumn(Measurement::getDiastolic).setHeader("Diastolic");
        measurementGrid.addColumn(Measurement::getWeight).setHeader("Weight");
        measurementGrid.setHeight("200px");
    }

    private void updateList() {
        String filter = filterField.getValue();
        if (filter == null || filter.isEmpty()) {
            personGrid.setItems(personService.findAll());
        } else {
            personGrid.setItems(personService.searchByName(filter));
        }
    }

    private void addPerson() {
        String name = nameField.getValue();
        String dob = dobField.getValue();
        if (name != null && !name.isEmpty()) {
            Person newPerson = new Person(name, dob);
            personService.save(newPerson);
            nameField.clear();
            dobField.clear();
            updateList();
        }
    }

    private void addMeasurement() {
        if (selectedPerson == null) return;

        Measurement m = new Measurement();
        m.setTimestamp(timestampField.getValue());
        m.setSystolic(systolicField.getValue().intValue());
        m.setDiastolic(diastolicField.getValue().intValue());
        m.setWeight(weightField.getValue());
        m.setPerson(selectedPerson);

        measurementService.save(m);

        timestampField.clear();
        systolicField.clear();
        diastolicField.clear();
        weightField.clear();

        measurementGrid.setItems(measurementService.findByPerson(selectedPerson));
    }
}
