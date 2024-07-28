package com.example.expensesharing.model;

import com.example.expensesharing.strategy.ExpenseSplitStrategy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "expenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String description;

    @ElementCollection
    @CollectionTable(name = "expense_split_amounts", joinColumns = @JoinColumn(name = "expense_id"))
    @MapKeyColumn(name = "user_email")
    @Column(name = "amount")
    private Map<String, Double> splitAmounts = new HashMap<>();

    @ManyToMany
    @JoinTable(name = "expense_participants", joinColumns = @JoinColumn(name = "expense_id"), inverseJoinColumns = @JoinColumn(name = "user_email"))
    private List<User> participants;

    @Transient
    private ExpenseSplitStrategy strategy;
}