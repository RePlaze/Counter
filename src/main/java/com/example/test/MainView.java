package com.example.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("main")
public class MainView extends VerticalLayout {



    private final CounterRepository counterRepository;
    private final NativeLabel valueLabel;
    private final Counter counter;

    public MainView(@Autowired CounterRepository counterRepository) {
        this.counterRepository = counterRepository;

        valueLabel = new NativeLabel();
        Button increaseButton = new Button("Increase");
        TextField valueField = new TextField("Value");

        counter = getOrCreateCounter();

        increaseButton.addClickListener(e -> {
            counterRepository.incrementCounter(counter.getId());
            updateValueLabel();
        });

        valueField.addValueChangeListener(e -> {
            int newValue = Integer.parseInt(e.getValue());
            counter.setValue(newValue);
            counterRepository.updateCounter(counter);
            updateValueLabel();
        });

        add(valueLabel, increaseButton, valueField);
        updateValueLabel();
    }

    private Counter getOrCreateCounter() {
        return counterRepository.findById(1L).orElseGet(() -> counterRepository.save(new Counter()));
    }

    private void updateValueLabel() {
        valueLabel.setText("Value: " + counter.getValue());
    }
}
